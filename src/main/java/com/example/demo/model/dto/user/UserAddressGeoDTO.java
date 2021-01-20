package com.example.demo.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserAddressGeoDTO {

    private Double lat;
    private Double lng;
}
