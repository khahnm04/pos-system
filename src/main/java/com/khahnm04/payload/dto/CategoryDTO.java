package com.khahnm04.payload.dto;

import com.khahnm04.model.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private Long storeId;

}
