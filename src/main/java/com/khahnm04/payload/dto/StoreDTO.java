package com.khahnm04.payload.dto;

import com.khahnm04.domain.StoreStatus;
import com.khahnm04.model.StoreContact;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StoreDTO {

    private Long id;
    private String brand;
    private UserDTO storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus status;
    private StoreContact contact;

}
