package com.example.monyorms.service;

import com.example.monyorms.DTOs.menyu.MenyuRequestDto;
import com.example.monyorms.DTOs.menyu.MenyuResponseDto;
import com.example.monyorms.entity.Menyu;
import com.example.monyorms.entity.Restaurant;
import com.example.monyorms.exception.MenyuNotFoundException;
import com.example.monyorms.mapper.MenyuMapper;
import com.example.monyorms.repository.MenyuRepository;
import com.example.monyorms.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info("Create menyu");
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> {
                    log.error("Restaurant not found with id={}", restaurantId);
                    return new RuntimeException("Restaurant not found");
                });
        Menyu menu = menyuMapper.toEntity(menyuRequestDto);
        menu.setRestaurant(restaurant);

        Menyu savedMenu = menyuRepository.save(menu);
        log.info("Menyu created successfully with id={}", savedMenu.getId());
        return menyuMapper.toResponseDto(savedMenu);
    }


    public List<MenyuResponseDto> getAllMenus() {
        log.info("Fetching all menyu entries");
        return menyuRepository.findAll()
                .stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MenyuResponseDto> getMenusByRestaurantId(Long restaurantId) {
        log.info("Fetching menyu by restaurantId={}", restaurantId);
        List<Menyu> menus = menyuRepository.findAllByRestaurantId(restaurantId);
        return menus.stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<MenyuResponseDto> getMenusByRestaurantName(String restaurantName) {
        log.info("Fetching menyu by restaurantName={}", restaurantName);
        List<Menyu> menus = menyuRepository.findAllByRestaurantName(restaurantName);
        return menus.stream()
                .map(menyuMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public MenyuResponseDto getMenuById(Long menyuId) {
        log.info("Fetching menyu by id={}", menyuId);
        Menyu menyu = menyuRepository.findById(menyuId)
                .orElseThrow(() -> {
                    log.error("Menyu not found with id={}", menyuId);
                    return new MenyuNotFoundException("Menyu tapilmadi");
                });
        return menyuMapper.toResponseDto(menyu);
    }

    public MenyuResponseDto updateMenu(Long menyuId, MenyuRequestDto menyuRequestDto) {
        Menyu menyu = menyuRepository.findById(menyuId)
                .orElseThrow(() -> new MenyuNotFoundException("Menyu tapilmadi"));

        menyu.setName(menyuRequestDto.getName());

        Menyu updatedMenyu = menyuRepository.save(menyu);
        return menyuMapper.toResponseDto(updatedMenyu);
    }

    public void deleteMenu(Long menyuId) {
        if (!menyuRepository.existsById(menyuId)) {
            throw new MenyuNotFoundException("Menyu tapilmadi");
        }
        menyuRepository.deleteById(menyuId);
    }

}
