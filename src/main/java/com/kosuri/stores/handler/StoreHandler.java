package com.kosuri.stores.handler;

import com.kosuri.stores.dao.StoreEntity;
import com.kosuri.stores.model.request.CreateStoreRequest;
import com.kosuri.stores.model.request.UpdateStoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class StoreHandler {
    @Autowired
    private RepositoryHandler repositoryHandler;
    public int addStore(CreateStoreRequest createStoreRequest){
        StoreEntity storeEntity = createStoreEntityFromRequest(createStoreRequest);

        repositoryHandler.addStoreToRepository(storeEntity);

        return storeEntity.getId();
    }

    public int updateStore(UpdateStoreRequest updateStoreRequest){
        StoreEntity storeEntity = updateStoreEntityFromRequest(updateStoreRequest);

        repositoryHandler.updateStore(storeEntity);

        return storeEntity.getId();
    }

    private StoreEntity createStoreEntityFromRequest(CreateStoreRequest createStoreRequest){
        //TODO add location and other fields from request instead of default values.
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setName(createStoreRequest.getName());
        storeEntity.setId(createStoreRequest.getId());
        storeEntity.setType("PHARMACY");
        storeEntity.setPincode(createStoreRequest.getPincode());
        storeEntity.setPincode(createStoreRequest.getPincode());
        storeEntity.setDistrict(createStoreRequest.getDistrict());
        storeEntity.setState(createStoreRequest.getState());
        storeEntity.setOwner(createStoreRequest.getOwner());
        storeEntity.setOwnerEmail(createStoreRequest.getOwnerEmail());
        storeEntity.setOwnerContact(createStoreRequest.getOwnerContact());
        storeEntity.setSecondaryContact(createStoreRequest.getSecondaryContact());
        storeEntity.setRegistrationDate(LocalDate.now().toString());
        storeEntity.setCreationTimeStamp(LocalDateTime.now().toString());
        storeEntity.setRole("test");
        storeEntity.setModifiedBy("test_user");
        storeEntity.setModifiedDate(LocalDate.now().toString());
        storeEntity.setModifiedTimeStamp(LocalDateTime.now().toString());
        storeEntity.setStatus("OPEN");
        storeEntity.setPassword("test");
        storeEntity.setAddedBy(createStoreRequest.getOwner());
        storeEntity.setLocation("abcd");

        return storeEntity;
    }

    private StoreEntity updateStoreEntityFromRequest(UpdateStoreRequest request){
        //TODO add location and other fields from request instead of default values.
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setName(request.getName());
        storeEntity.setId(request.getId());
        storeEntity.setType("PHARMACY");
        storeEntity.setPincode(request.getPincode());
        storeEntity.setPincode(request.getPincode());
        storeEntity.setDistrict(request.getDistrict());
        storeEntity.setState(request.getState());
        storeEntity.setOwner(request.getOwner());
        storeEntity.setOwnerEmail(request.getOwnerEmail());
        storeEntity.setOwnerContact(request.getOwnerContact());
        storeEntity.setSecondaryContact(request.getSecondaryContact());
        storeEntity.setRegistrationDate(LocalDate.now().toString());
        storeEntity.setCreationTimeStamp(LocalDateTime.now().toString());
        storeEntity.setRole("test");
        storeEntity.setModifiedBy("test_user");
        storeEntity.setModifiedDate(LocalDate.now().toString());
        storeEntity.setModifiedTimeStamp(LocalDateTime.now().toString());
        storeEntity.setStatus("OPEN");
        storeEntity.setPassword("test");
        storeEntity.setAddedBy(request.getOwner());
        storeEntity.setLocation("abcd");

        return storeEntity;
    }
}
