package com.khahnm04.controller;

import com.khahnm04.domain.StoreStatus;
import com.khahnm04.exception.UserException;
import com.khahnm04.mapper.StoreMapper;
import com.khahnm04.model.User;
import com.khahnm04.payload.dto.StoreDTO;
import com.khahnm04.payload.response.ApiResponse;
import com.khahnm04.service.StoreService;
import com.khahnm04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(
        @RequestBody StoreDTO storeDTO,
        @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDTO, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(
        @PathVariable Long id,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getStoreById(
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
        @PathVariable Long id,
        @RequestBody StoreDTO storeDTO
    ) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(
        @PathVariable Long id,
        @RequestParam StoreStatus status
    ) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(
        @PathVariable Long id
    ) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
