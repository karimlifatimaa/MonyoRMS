package com.example.monyorms.DTOs.table;

import lombok.Data;

@Data
public class TableResponseDto {
    private Long id;
    private String code;
    private boolean occupied;
}
