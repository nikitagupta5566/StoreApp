package com.kosuri.stores.handler;


import com.kosuri.stores.dao.StockEntity;
import com.kosuri.stores.dao.StockRepository;
import com.kosuri.stores.model.enums.StockUpdateRequestType;
import com.kosuri.stores.model.request.StockUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHandler {

    @Autowired
    StockRepository stockRepository;

    public void updateStock(StockUpdateRequest stockUpdateRequest) {

        StockEntity stock = stockRepository.findByMfNameAndItemCodeAndItemNameAndBatchAndSupplierNameAndManufacturerAndStoreId(
                stockUpdateRequest.getMfName(), stockUpdateRequest.getItemCode(), stockUpdateRequest.getItemName(), stockUpdateRequest.getBatch(), stockUpdateRequest.getSupplierName(), stockUpdateRequest.getManufacturer(), stockUpdateRequest.getStoreId());

        if (stock != null) {
            Double currBalLooseQuantity = stock.getBalLooseQuantity();
            Double currBalPackQuantity = stock.getBalPackQuantity();
            Double curBalQuantity = stock.getBalQuantity();

            if (stockUpdateRequest.getStockUpdateRequestType() == StockUpdateRequestType.PURCHASE) {
                currBalLooseQuantity += stockUpdateRequest.getBalLooseQuantity();
                currBalPackQuantity += stockUpdateRequest.getBalPackQuantity();
                curBalQuantity += stockUpdateRequest.getBalQuantity();
            } else {
                currBalLooseQuantity -= stockUpdateRequest.getBalLooseQuantity();
                currBalPackQuantity -= stockUpdateRequest.getBalPackQuantity();
                curBalQuantity -= stockUpdateRequest.getBalQuantity();
            }

            stock.setBalPackQuantity(currBalPackQuantity);
            stock.setBalLooseQuantity(currBalLooseQuantity);
            stock.setBalQuantity(curBalQuantity);
            stock.setMrpValue(curBalQuantity * stock.getMrpPack());
            stockRepository.save(stock);

        } else {
            StockEntity s = new StockEntity();
            s.setItemName(stockUpdateRequest.getItemName());
            s.setBatch(stockUpdateRequest.getBatch());
            s.setManufacturer(stockUpdateRequest.getManufacturer());
            s.setMfName(stockUpdateRequest.getMfName());
            s.setSupplierName(stockUpdateRequest.getSupplierName());
            s.setItemCode(stockUpdateRequest.getItemCode());
            s.setBalLooseQuantity(stockUpdateRequest.getBalLooseQuantity());
            s.setBalPackQuantity(stockUpdateRequest.getBalPackQuantity());
            s.setBalQuantity(stockUpdateRequest.getBalQuantity());
            s.setExpiryDate(stockUpdateRequest.getExpiryDate());
            s.setOnlineYesNo("Yes");
            s.setStoreId(stockUpdateRequest.getStoreId());
            s.setMrpValue(stockUpdateRequest.getBalQuantity() * stockUpdateRequest.getMrpPack());

            stockRepository.save(s);
        }

    }
}


