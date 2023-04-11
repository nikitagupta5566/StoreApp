package com.kosuri.stores.controller;

import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.handler.StoreHandler;
import com.kosuri.stores.model.request.CreateStoreRequest;
import com.kosuri.stores.model.request.UpdateStoreRequest;
import com.kosuri.stores.model.response.CreateStoreResponse;
import com.kosuri.stores.model.response.UpdateStoreResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreHandler storeHandler;

    @PostMapping("/create")
    ResponseEntity<CreateStoreResponse> createStore(@Valid @RequestBody CreateStoreRequest request) {
        CreateStoreResponse createStoreResponse = new CreateStoreResponse();
        HttpStatus httpStatus;
        try {
            createStoreResponse.setId(storeHandler.addStore(request));
            httpStatus = HttpStatus.OK;
        } catch (APIException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            createStoreResponse.setResponseMessage(e.getMessage());
        }
        return ResponseEntity.status(httpStatus).body(createStoreResponse);
    }

    @PostMapping("/update")
    ResponseEntity<UpdateStoreResponse> updateStore(@Valid @RequestBody UpdateStoreRequest request) {
        HttpStatus httpStatus;
        UpdateStoreResponse updateStoreResponse = new UpdateStoreResponse();

        try {
            String storeId = storeHandler.updateStore(request);
            httpStatus = HttpStatus.OK;
            updateStoreResponse.setId(storeId);
        } catch (APIException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            updateStoreResponse.setResponseMessage(e.getMessage());
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            updateStoreResponse.setResponseMessage(e.getMessage());
        }

        return ResponseEntity.status(httpStatus).body(updateStoreResponse);
    }
}
