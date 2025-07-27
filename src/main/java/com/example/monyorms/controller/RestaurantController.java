package com.example.monyorms.controller;

import com.example.monyorms.DTOs.menyu.MenyuRequestDto;
import com.example.monyorms.DTOs.menyu.MenyuResponseDto;
import com.example.monyorms.DTOs.restaurant.RestaurantRequestDto;
import com.example.monyorms.DTOs.restaurant.RestaurantResponseDto;
import com.example.monyorms.DTOs.table.TableRequestDto;
import com.example.monyorms.DTOs.table.TableResponseDto;
import com.example.monyorms.service.MenyuService;
import com.example.monyorms.service.RestaurantService;
import com.example.monyorms.service.TableService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenyuService menyuService;
    private final TableService tableService;

    public RestaurantController(RestaurantService restaurantService,
                                MenyuService menyuService,
                                TableService tableService) {
        this.restaurantService = restaurantService;
        this.menyuService = menyuService;
        this.tableService = tableService;
    }

    // === RESTAURANT CRUD ===

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> create(@RequestBody @Valid RestaurantRequestDto dto) {
        return ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid RestaurantRequestDto dto) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === TABLE ƏMƏLİYYATLARI ===

    @PostMapping("/{restaurantId}/tables")
    public ResponseEntity<TableResponseDto> addTable(@PathVariable Long restaurantId,
                                                     @RequestBody @Valid TableRequestDto dto) {
        return ResponseEntity.ok(tableService.createTable(restaurantId, dto));
    }

    @GetMapping("/{restaurantId}/tables")
    public ResponseEntity<List<TableResponseDto>> getTablesByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(tableService.getAllTablesByRestaurantId(restaurantId));
    }

    @GetMapping("/tables/by-name")
    public ResponseEntity<List<TableResponseDto>> getTablesByRestaurantName(@RequestParam String name) {
        return ResponseEntity.ok(tableService.getTablesByRestaurantName(name));
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<TableResponseDto> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @PutMapping("/tables/{id}")
    public ResponseEntity<TableResponseDto> updateTable(@PathVariable Long id,
                                                        @RequestBody @Valid TableRequestDto dto) {
        return ResponseEntity.ok(tableService.updateTable(id, dto));
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    // === MENYU ƏMƏLİYYATLARI ===

    @PostMapping("/{restaurantId}/menus")
    public ResponseEntity<MenyuResponseDto> addMenu(@PathVariable Long restaurantId,
                                                    @RequestBody @Valid MenyuRequestDto dto) {
        return ResponseEntity.ok(menyuService.createMenyu(restaurantId, dto));
    }

    @GetMapping("/{restaurantId}/menus")
    public ResponseEntity<List<MenyuResponseDto>> getMenusByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menyuService.getMenusByRestaurantId(restaurantId));
    }

    @GetMapping("/menus/by-name")
    public ResponseEntity<List<MenyuResponseDto>> getMenusByRestaurantName(@RequestParam String name) {
        return ResponseEntity.ok(menyuService.getMenusByRestaurantName(name));
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<MenyuResponseDto> getMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(menyuService.getMenuById(id));
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<MenyuResponseDto> updateMenu(@PathVariable Long id,
                                                       @RequestBody @Valid MenyuRequestDto dto) {
        return ResponseEntity.ok(menyuService.updateMenu(id, dto));
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menyuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
