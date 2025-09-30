package com.khahnm04.service;

import com.khahnm04.domain.StoreStatus;
import com.khahnm04.exception.UserException;
import com.khahnm04.model.Store;
import com.khahnm04.model.User;
import com.khahnm04.payload.dto.StoreDTO;
import java.util.List;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO, User user);
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long id, StoreDTO storeDTO) throws UserException;
    void deleteStore(Long id) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;
    StoreDTO moderateStore(Long id, StoreStatus status) throws Exception;

}
