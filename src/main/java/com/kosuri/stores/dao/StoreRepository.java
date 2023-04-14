package com.kosuri.stores.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, String> {
    Boolean existsByOwnerEmail(String ownerEmail);
    Boolean existsByOwnerContact(String ownerContact);

    Optional<List<StoreEntity>> findByOwnerEmail(String ownerEmail);
    Optional<List<StoreEntity>> findByOwnerContact(String ownerContact);

    Optional<List<StoreEntity>> findByOwnerEmailOrOwnerContact(String ownerEmail, String ownerContact);

    Optional<List<StoreEntity>> findByLocationContaining(String location);
}
