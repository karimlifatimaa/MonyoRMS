package com.example.monyorms.service;

import com.example.monyorms.DTOs.restaurant.RestaurantRequestDto;
import com.example.monyorms.DTOs.restaurant.RestaurantResponseDto;
import com.example.monyorms.entity.Restaurant;
import com.example.monyorms.exception.RestourantNotFoundException;
import com.example.monyorms.mapper.RestaurantMapper;
import com.example.monyorms.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {

        Restaurant restaurant = restaurantRepository.save(restaurantMapper.toEntity(restaurantRequestDto));
        return restaurantMapper.toResponseDto(restaurant);
    }

    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public RestaurantResponseDto getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId).orElseThrow(() -> new RestourantNotFoundException("Restaurant not found"));
        return restaurantMapper.toResponseDto(restaurant);
    }

    public RestaurantResponseDto updateRestaurant(Long id,RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestourantNotFoundException("Restaurant not found"));

        restaurant.setName(restaurantRequestDto.getName());
        restaurant.setAddress(restaurantRequestDto.getAddress());
        restaurant.setQrCodeUrl(restaurantRequestDto.getQrCodeUrl());
        Restaurant updated = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDto(updated);
    }

    public void delete(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestourantNotFoundException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }
}
