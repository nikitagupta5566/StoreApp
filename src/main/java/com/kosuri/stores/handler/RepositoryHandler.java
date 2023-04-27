package com.kosuri.stores.handler;

import com.kosuri.stores.dao.*;
import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.model.enums.Role;
import com.kosuri.stores.model.request.AddUserRequest;
import com.kosuri.stores.model.request.LoginUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryHandler {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SaleRepository saleRepository;

    public StoreEntity addStoreToRepository(@Valid StoreEntity storeEntity) throws Exception {
        Optional<StoreEntity> store = storeRepository.findById(storeEntity.getId());
        boolean storeExistsByOwnerEmail = storeRepository.existsByOwnerEmail(storeEntity.getOwnerEmail());
        if (store.isPresent()) {
            throw new APIException("Store with this id is already present");
        }

        if(storeExistsByOwnerEmail) {
            throw new APIException("Store with owner email is already present");
        }
        return storeRepository.save(storeEntity);

    }

    public StoreEntity updateStore(@Valid StoreEntity storeEntity) throws Exception {
        Optional<StoreEntity> store = storeRepository.findById(storeEntity.getId());

        if(store.isEmpty()){
            System.out.println("Entity not found");
            throw new APIException("Store with this id not found!");
        }

        return storeRepository.save(storeEntity);
    }

    public void addUser(@Valid StoreEntity storeEntity, AddUserRequest request) throws Exception {
        //TODO Update to query based on id
        Optional<StoreEntity> store = storeRepository.findById(request.getStoreId());
        if(request.getRole().equals(Role.STORE_MANAGER) && store.isEmpty()){
            System.out.println("No store found for user");
            throw new APIException("Store with this id not found for user!");
        }
        if(!store.isEmpty()) {
            store.get().setOwner(request.getName());
            store.get().setOwnerEmail(request.getEmail());
            store.get().setOwnerContact(request.getPhoneNumber());

            storeRepository.save(store.get());
        }
        storeRepository.save(storeEntity);

    }

    public List<StoreEntity> getAllStores() throws Exception {
        List<StoreEntity> storeEntities = storeRepository.findAll();
        return storeEntities;
    }


    public Optional<List<PurchaseEntity>> getPurchaseRecordsByStore(String storeId){
        return purchaseRepository.findByStoreId(storeId);
    }

    public Optional<List<SaleEntity>> getSaleRecordsByStore(String storeId){
        return saleRepository.findByStoreId(storeId);
    }

    public boolean validateuser(AddUserRequest request) throws Exception{
        Optional<List<StoreEntity>> existingStores = storeRepository.findByOwnerEmailOrOwnerContact(request.getEmail(), request.getPhoneNumber());
        if(existingStores.isEmpty()){
            return true;
        }
        for (StoreEntity store: existingStores.get()){
            //TODO Update to query based on id
            if (store.getName().contains("DUMMY")){
                System.out.println("User already exists in system");
                throw new APIException("User already exists in system");
            }
        }

        return true;
    }

    public boolean loginUser(LoginUserRequest request) throws Exception {
        Optional<List<StoreEntity>> existingStores = storeRepository.findByOwnerEmailOrOwnerContact(request.getEmail(), request.getPhoneNumber());
        if (existingStores.isEmpty()) {
            throw new APIException("Invalid Credentials!");
        }

        for (StoreEntity store : existingStores.get()) {
            //TODO Update to query based on id
            if (store.getPassword().equals(request.getPassword())) {
                System.out.println("User logged in successfully");
                return true;
            }
        }

        throw new APIException("Invalid Credentials!");
    }

}
