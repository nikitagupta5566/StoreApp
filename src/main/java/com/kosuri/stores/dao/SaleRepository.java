package com.kosuri.stores.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import java.util.Date;
import java.util.Optional;


@Repository
public interface SaleRepository  extends JpaRepository<SaleEntity, Integer> {
    Optional<List<SaleEntity>> findByStoreId(String storeId);

    @Query("Select sum(s.saleValue) from SaleEntity s where s.custName = ?1 and s.date > ?2")
    public Double findTotalSalesForCustomerAfterDate(String customerName, Date date);

    @Query("Select sum(s.saleValue) from SaleEntity s where (s.mobile = ?1 or s.custName = ?2) and s.date > ?3")
    public Double findTotalSalesForCustomerPhoneOrNameAfterDate(String mobile, String customerName, Date date);

    @Query("Select sum(s.saleValue) from SaleEntity s where (s.mobile = ?1 and s.custName = ?2) and s.date > ?3")
    public Double findTotalSalesForCustomerPhoneAndNameAfterDate(String mobile, String customerName, Date date);
}

