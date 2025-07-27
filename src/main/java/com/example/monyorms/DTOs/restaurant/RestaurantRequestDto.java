package com.example.monyorms.DTOs.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String qrCodeUrl;
}
