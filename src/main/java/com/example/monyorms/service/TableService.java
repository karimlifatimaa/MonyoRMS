package com.example.monyorms.service;

import com.example.monyorms.DTOs.table.TableRequestDto;
import com.example.monyorms.DTOs.table.TableResponseDto;
import com.example.monyorms.entity.DiningTable;
import com.example.monyorms.entity.Restaurant;
import com.example.monyorms.exception.TableNotFoundException;
import com.example.monyorms.mapper.TableMapper;
import com.example.monyorms.repository.RestaurantRepository;
import com.example.monyorms.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableMapper tableMapper;

    public TableService(TableRepository tableRepository,
                        RestaurantRepository restaurantRepository,
                        TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableMapper = tableMapper;
    }
    public TableResponseDto createTable(Long restaurantId, TableRequestDto tableRequestDto) {


        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant tapılmadı"));

        DiningTable table = tableMapper.toEntity(tableRequestDto);

        table.setRestaurant(restaurant);
       // restaurant.getDiningTables().add(table);

        tableRepository.save(table);

        return tableMapper.toResponseDto(table);
    }

    public List<TableResponseDto> getAllTables() {
        return tableRepository.findAll()
                .stream()
                .map(tableMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<TableResponseDto> getAllTablesByRestaurantId(Long restaurantId) {
        List<DiningTable> diningTables = tableRepository.findAllByRestaurantId(restaurantId);
        return diningTables.stream()
                .filter(table -> !table.isOccupied()) // yalnız boş masalar
                .map(tableMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<TableResponseDto> getTablesByRestaurantName(String restaurantName) {
        List<DiningTable> diningTables = tableRepository.findAllByRestaurantName(restaurantName);
        return diningTables.stream()
                .map(tableMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public TableResponseDto getTableById(Long id) {
        DiningTable diningTable = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Masa tapılmadı"));
        return tableMapper.toResponseDto(diningTable);
    }

    public TableResponseDto updateTable(Long id, TableRequestDto dto) {
        DiningTable diningTable = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Masa tapılmadı"));

        diningTable.setCode(dto.getCode());
        diningTable.setOccupied(dto.isOccupied());

        DiningTable updated = tableRepository.save(diningTable);
        return tableMapper.toResponseDto(updated);
    }
    public void deleteTable(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new TableNotFoundException("Masa tapılmadı");
        }
        tableRepository.deleteById(id);
    }
}
