package com.khahnm04.repository;

import com.khahnm04.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long adminId);

}
