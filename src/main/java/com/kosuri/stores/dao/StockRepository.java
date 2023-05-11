package com.kosuri.stores.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, StockId> {

    public StockEntity findByMfNameAndItemNameAndBatchAndStoreIdAndSupplierName(String mFName, String itemName, String batch, String storeId, String supplierName);

    public List<StockEntity> findByItemNameContainingAndStoreIdAndItemCategoryAndBalQuantityGreaterThan(String itemName, String storeId, String category, Double balQuantity);

    public List<StockEntity> findByItemNameContainingAndStoreIdAndBalQuantityGreaterThan(String itemName, String storeId, Double balQuantity);

    Optional<List<StockEntity>> findByStoreId(String storeId);
}

