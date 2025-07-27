package com.example.monyorms.DTOs.restaurant;

import com.example.monyorms.DTOs.menyu.MenyuResponseDto;
import com.example.monyorms.DTOs.table.TableResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String qrCodeUrl;
    private List<TableResponseDto> diningTables;
    private List<MenyuResponseDto> menus;
    private String data;
}
