package com.kosuri.stores.model.response;

import com.kosuri.stores.dao.StoreEntity;
import com.kosuri.stores.model.Store;

import java.util.List;

public class GetAllStoreResponse  extends GenericResponse{
    private List<StoreEntity> stores;

    public List<StoreEntity> getStores() {
        return stores;
    }

    public void setStores(List<StoreEntity> stores) {
        this.stores = stores;
    }
}
