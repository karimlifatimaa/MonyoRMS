package com.example.monyorms.service;

import com.example.monyorms.DTOs.menyu.MenyuRequestDto;
import com.example.monyorms.DTOs.menyu.MenyuResponseDto;
import com.example.monyorms.entity.Menyu;
import com.example.monyorms.entity.Restaurant;
import com.example.monyorms.mapper.MenyuMapper;
import com.example.monyorms.repository.MenyuRepository;
import com.example.monyorms.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenyuService {
    private final MenyuRepository menyuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenyuMapper menyuMapper;

    public MenyuService(MenyuRepository menuRepository, RestaurantRepository restaurantRepository, MenyuMapper menyuMapper) {
        this.menyuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.menyuMapper = menyuMapper;
    }

    public MenyuResponseDto createMenyu(Long restaurantId, MenyuRequestDto menyuRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Menyu menu = menyuMapper.toEntity(menyuRequestDto);
        menu.setRestaurant(restaurant);

        Menyu savedMenu = menyuRepository.save(menu);
        return menyuMapper.toResponseDto(savedMenu);
    }


    public List<MenyuResponseDto> getAllMenus() {
        return menyuRepository.findAll()
                .stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MenyuResponseDto> getMenusByRestaurantId(Long restaurantId) {
        List<Menyu> menus = menyuRepository.findAllByRestaurantId(restaurantId);
        return menus.stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MenyuResponseDto> getMenusByRestaurantName(String restaurantName) {
        List<Menyu> menus = menyuRepository.findAllByRestaurantName(restaurantName);
        return menus.stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public MenyuResponseDto getMenuById(Long menyuId) {
        Menyu menyu = menyuRepository.findById(menyuId)
                .orElseThrow(() -> new RuntimeException("Menyu tapilmadi"));
        return menyuMapper.toResponseDto(menyu);
    }

    public MenyuResponseDto updateMenu(Long menyuId, MenyuRequestDto menyuRequestDto) {
        Menyu menyu = menyuRepository.findById(menyuId)
                .orElseThrow(() -> new RuntimeException("Menyu tapilmadi"));

        menyu.setName(menyuRequestDto.getName());

        Menyu updatedMenyu = menyuRepository.save(menyu);
        return menyuMapper.toResponseDto(updatedMenyu);
    }

    public void deleteMenu(Long menyuId) {
        if (!menyuRepository.existsById(menyuId)) {
            throw new RuntimeException("Menyu tapilmadi");
        }
        menyuRepository.deleteById(menyuId);
    }

}
