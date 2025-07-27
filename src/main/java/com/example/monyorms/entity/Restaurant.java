package com.example.monyorms.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String qrCodeUrl;
    private String data;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DiningTable> diningTables = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Menyu> menus = new ArrayList<>();
}
