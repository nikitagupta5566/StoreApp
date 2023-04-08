package com.kosuri.stores.handler;

import com.kosuri.stores.dao.PurchaseEntity;
import com.kosuri.stores.dao.PurchaseRepository;
import com.kosuri.stores.dao.StoreEntity;
import com.kosuri.stores.dao.StoreRepository;
import com.kosuri.stores.model.Store;
import com.kosuri.stores.model.request.AddUserRequest;
import com.kosuri.stores.model.request.LoginUserRequest;
import com.kosuri.stores.model.request.AddUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RepositoryHandler {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public void addStoreToRepository(@Valid StoreEntity storeEntity){
        long stores = storeRepository.count();
        System.out.println("fetching repository " + stores);
        try {
            storeRepository.save(storeEntity);
        }catch(Exception e){
            System.out.println(e.getCause());
        }
    }

    public void updateStore(@Valid StoreEntity storeEntity){
        Optional<StoreEntity> store = storeRepository.findById(storeEntity.getId());
        if(store.isEmpty()){
            System.out.println("Entity not found");
            return;
        }
        try {
            storeRepository.save(storeEntity);
        } catch(Exception e) {
            System.out.println(e.getCause());
        }
    }

    public void addUser(@Valid StoreEntity storeEntity, AddUserRequest request) {
        //TODO Update to query based on id
        Optional<StoreEntity> store = storeRepository.findById("123");
        store.get().setOwner(request.getName());
        store.get().setOwnerEmail(request.getEmail());
        store.get().setOwnerContact(request.getPhoneNumber());
        try {
            storeRepository.save(store.get());
            storeRepository.save(storeEntity);
        } catch(Exception e) {
            System.out.println(e.getCause());
        }
    }

    public Optional<List<PurchaseEntity>> getPurchaseRecordsByStore(String storeId){
        return purchaseRepository.findByStoreId(storeId);
    }

    public boolean validateuser(AddUserRequest request){
        Optional<List<StoreEntity>> existingStores = storeRepository.findByOwnerEmailOrOwnerContact(request.getEmail(), request.getPhoneNumber());
        if(existingStores.isEmpty()){
            return true;
        }
        for (StoreEntity store: existingStores.get()){
            //TODO Update to query based on id
            if (store.getName().contains("DUMMY")){
                System.out.println("User already exists in system");
                return false;
            }
        }

        return true;
    }

    public boolean loginUser(LoginUserRequest request) {
        Optional<List<StoreEntity>> existingStores = storeRepository.findByOwnerEmailOrOwnerContact(request.getEmail(), request.getPhoneNumber());
        if (existingStores.isEmpty()) {
            return false;
        }

        for (StoreEntity store : existingStores.get()) {
            //TODO Update to query based on id
            if (store.getPassword().equals(request.getPassword())) {
                System.out.println("User logged in successfully");
                return true;
            }
        }

        return false;
    }
}
