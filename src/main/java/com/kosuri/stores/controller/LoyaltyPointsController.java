package com.kosuri.stores.controller;

import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.handler.LoyaltyPointsHandler;
import com.kosuri.stores.model.request.ConfigureLoyaltyPointsRequest;
import com.kosuri.stores.model.request.CustomerLoyaltyRequest;
import com.kosuri.stores.model.request.RedeemLoyaltyPointsRequest;
import com.kosuri.stores.model.response.ConfigureLoyaltyPointsResponse;
import com.kosuri.stores.model.response.CustomerLoyaltyResponse;
import com.kosuri.stores.model.response.RedeemLoyaltyPointsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loyalty/points")
public class LoyaltyPointsController {

    @Autowired
    LoyaltyPointsHandler loyaltyPointsHandler;

    @PostMapping("/configure")
    public ResponseEntity<ConfigureLoyaltyPointsResponse> configureLoyaltyPoints(@Valid @RequestBody ConfigureLoyaltyPointsRequest request) throws Exception {
        ConfigureLoyaltyPointsResponse response = new ConfigureLoyaltyPointsResponse();
        try {
            response = loyaltyPointsHandler.configureLoyaltyPoints(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setResponseMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping("/redeem")
    public ResponseEntity<RedeemLoyaltyPointsResponse> redeemLoyaltyPointsResponse(@Valid @RequestBody RedeemLoyaltyPointsRequest request) {
        try {
            loyaltyPointsHandler.redeemLoyaltyPointsForCustomer(request);
        } catch (Exception e) {

        }

        return null;
    }

    @PostMapping("/checkDiscount")
    public ResponseEntity<CustomerLoyaltyResponse> checkDiscount(@Valid @RequestBody CustomerLoyaltyRequest request) {
        CustomerLoyaltyResponse response = new CustomerLoyaltyResponse();
        try {
            response = loyaltyPointsHandler.getDiscountForCustomer(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (APIException e) {
            response.setResponseMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception e) {
            response.setResponseMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
