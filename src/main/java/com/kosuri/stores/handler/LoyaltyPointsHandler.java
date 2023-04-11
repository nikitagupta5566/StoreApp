package com.kosuri.stores.handler;

import com.kosuri.stores.dao.*;
import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.model.request.ConfigureLoyaltyPointsRequest;
import com.kosuri.stores.model.request.CustomerLoyaltyRequest;
import com.kosuri.stores.model.request.RedeemLoyaltyPointsRequest;
import com.kosuri.stores.model.response.ConfigureLoyaltyPointsResponse;
import com.kosuri.stores.model.response.CustomerLoyaltyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoyaltyPointsHandler {

    @Autowired
    LoyaltyRepository loyaltyRepository;

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    CustomerLoyaltyRepository customerLoyaltyRepository;

    public ConfigureLoyaltyPointsResponse configureLoyaltyPoints(ConfigureLoyaltyPointsRequest request) throws Exception {
        LoyaltyEntity loyaltyEntity = new LoyaltyEntity();
        ConfigureLoyaltyPointsResponse response = new ConfigureLoyaltyPointsResponse();

        loyaltyEntity.setStoreId(request.getStoreId());
        loyaltyEntity.setSalesVolume(request.getSalesVolume());
        loyaltyEntity.setLoyaltyPoints(request.getLoyaltyPoints());
        loyaltyEntity.setFixedDiscountPercentage(request.getFixedDiscountPercentage());
        loyaltyEntity.setMinLoyaltyPoints(request.getMinLoyaltyPoints());

        Double totalSalesVolume = request.getMinLoyaltyPoints()*(request.getSalesVolume()/request.getLoyaltyPoints());
        Double totalDiscountPercentage = request.getFixedDiscountPercentage() * request.getMinLoyaltyPoints()/100D;
        Double totalDiscount = (totalSalesVolume * totalDiscountPercentage)/100;
        loyaltyEntity.setTotalSalesVolume(totalSalesVolume);

        loyaltyRepository.save(loyaltyEntity);

        response.setTotalSalesVolume(totalSalesVolume);
        response.setTotalDiscount(totalDiscount);

        return response;
    }

    public void redeemLoyaltyPointsForCustomer(RedeemLoyaltyPointsRequest request) throws Exception {
        CustomerLoyaltyEntity customerLoyaltyEntity = new CustomerLoyaltyEntity();
        customerLoyaltyEntity.setLoyaltyPoints(request.getLoyaltyPoints());
        customerLoyaltyEntity.setCustomerName(request.getFirstName());
        customerLoyaltyEntity.setCustomerPhone(request.getCustomerPhone());
        customerLoyaltyEntity.setTotalSales(request.getTotalSales());
        customerLoyaltyEntity.setDiscountAmount(request.getDiscountAmount());
        customerLoyaltyEntity.setDiscountedDate(request.getDateOfDiscount());

        customerLoyaltyRepository.save(customerLoyaltyEntity);
    }

    public CustomerLoyaltyResponse getDiscountForCustomer(CustomerLoyaltyRequest request) throws Exception {
        Optional<CustomerLoyaltyEntity> customerLoyaltyEntityOptional = customerLoyaltyRepository.findByCustomerNameAndCustomerPhoneAndFirstByOrderByDiscountedDateDsc(request.getCustomerName(), request.getCustomerPhone());
        CustomerLoyaltyResponse response = new CustomerLoyaltyResponse();
        response.setFirstName(request.getCustomerName());
        response.setPhoneNumber(request.getCustomerPhone());

        if (customerLoyaltyEntityOptional.isPresent()) {
            CustomerLoyaltyEntity customerLoyaltyEntity = customerLoyaltyEntityOptional.get();
            Date lastDiscountDate = customerLoyaltyEntity.getDiscountedDate();
            Double totalSaleAfterDate = saleRepository.findTotalSalesForCustomerAfterDate(request.getCustomerName(), lastDiscountDate);

            Optional<LoyaltyEntity> storeLoyaltyOptional = loyaltyRepository.findById(request.getStoreId());
            if (storeLoyaltyOptional.isEmpty()) {
                throw new APIException("No loyalty points configured for this store");
            }

            LoyaltyEntity storeLoyalty = storeLoyaltyOptional.get();

            Long pointsEarned = Math.round((totalSaleAfterDate / storeLoyalty.getSalesVolume()) * storeLoyalty.getLoyaltyPoints());
            Double totalDiscPercentage = 0D;
            Double totalDiscountAmount = 0D;

            if (pointsEarned > storeLoyalty.getMinLoyaltyPoints()) {
                totalDiscPercentage = storeLoyalty.getFixedDiscountPercentage() * (pointsEarned / 100);
                totalDiscountAmount = (totalDiscPercentage * totalSaleAfterDate)/100;
            }

            response.setLoyaltyPoints(pointsEarned.intValue());
            response.setDiscountEligible(totalDiscountAmount);
            response.setTotalSalesVolume(totalSaleAfterDate);
        }

        return response;
    }
}
