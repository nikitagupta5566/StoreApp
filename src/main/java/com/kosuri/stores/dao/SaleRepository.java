package com.kosuri.stores.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleRepository  extends JpaRepository<SaleEntity, Integer> {
}

