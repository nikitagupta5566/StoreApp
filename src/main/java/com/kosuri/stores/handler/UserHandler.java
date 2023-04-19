package com.kosuri.stores.handler;

import com.kosuri.stores.dao.StoreEntity;
import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.model.request.AddUserRequest;
import com.kosuri.stores.model.request.LoginUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserHandler {
    @Autowired
    private RepositoryHandler repositoryHandler;

    public boolean addUser(AddUserRequest request) throws Exception {
        if(!repositoryHandler.validateuser(request)){
            return false;
        }
        StoreEntity userStoreEntity = getEntityFromUserRequest(request);

        repositoryHandler.addUser(userStoreEntity, request);

        return true;
    }

    public boolean loginUser(LoginUserRequest request) throws Exception {
        if (request.getEmail() == null && request.getPhoneNumber() == null) {
            throw new APIException("email and phone number both can't be null");
        }

        if(repositoryHandler.loginUser(request)){
            return true;
        }
        return false;
    }

    private StoreEntity getEntityFromUserRequest(AddUserRequest request){
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setOwner(request.getName());
        storeEntity.setOwnerContact(request.getPhoneNumber());
        storeEntity.setOwnerEmail(request.getEmail());
        storeEntity.setLocation(request.getAddress());
        storeEntity.setRole(request.getRole().toString());
        storeEntity.setPassword(request.getPassword());

        //setting dummy parameters.
        storeEntity.setId("DUMMY" + request.getPhoneNumber());
//        storeEntity.setId(((int) Math.random()));
        storeEntity.setType("DUMMY");
        storeEntity.setName("DUMMY");
        storeEntity.setPincode("DUMMY");
        storeEntity.setDistrict("DUMMY");
        storeEntity.setState("DUMMY");
        storeEntity.setSecondaryContact("DUMMY");
        storeEntity.setRegistrationDate(LocalDate.now().toString());
        storeEntity.setCreationTimeStamp(LocalDateTime.now().toString());
        storeEntity.setModifiedBy("test_user");
        storeEntity.setModifiedDate(LocalDate.now().toString());
        storeEntity.setModifiedTimeStamp(LocalDateTime.now().toString());
        storeEntity.setStatus("DUMMY");
        storeEntity.setAddedBy("test_user");

        return storeEntity;
    }
}
