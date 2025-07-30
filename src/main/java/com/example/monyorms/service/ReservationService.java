package com.example.monyorms.service;

import com.example.monyorms.DTOs.reservation.ReservationRequestDto;
import com.example.monyorms.DTOs.reservation.ReservationResponseDto;
import com.example.monyorms.DTOs.reservation.ReservationUpdateDto;
import com.example.monyorms.entity.DiningTable;
import com.example.monyorms.entity.Reservation;
import com.example.monyorms.entity.enums.ReservationStatus;
import com.example.monyorms.exception.TableNotFoundException;
import com.example.monyorms.exception.TableOccupiedException;
import com.example.monyorms.repository.ReservationRepository;
import com.example.monyorms.repository.RestaurantRepository;
import com.example.monyorms.repository.TableRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public ReservationService(ReservationRepository reservationRepository, TableRepository tableRepository, RestaurantRepository restaurantRepository) {
        this.reservationRepository = reservationRepository;
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public ReservationResponseDto createReservation(ReservationRequestDto request){
        DiningTable diningTable = tableRepository.findById(request.getTableId())
                .orElseThrow(()-> new TableNotFoundException("Table not found"));

        if (isTableOccupied(request.getTableId(), request.getReservationStart(), request.getReservationEnd())) {
            throw new TableNotFoundException("Masa bu vaxt aralığı üçün artıq bron edilmişdir."); // <-- Bu olmalıdır
        }

        Reservation reservation = new Reservation();
        reservation.setCustomerName(request.getCustomerName());
        reservation.setPhoneNumber(request.getPhoneNumber());
        reservation.setReservationStart(request.getReservationStart());
        reservation.setReservationEnd(request.getReservationEnd());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setDiningTable(diningTable);
        Reservation savedReservation = reservationRepository.save(reservation);
        return mapToReservationResponseDto(savedReservation);
    }

    @Transactional
    public ReservationResponseDto updateReservationStatus(ReservationUpdateDto updateDto){
        Reservation reservation = reservationRepository.findById(updateDto.getReservationId())
                .orElseThrow(() -> new TableNotFoundException("Table not found"));

        reservation.setStatus(updateDto.getNewStatus());
        reservationRepository.save(reservation);
        return mapToReservationResponseDto(reservation);
    }
    private boolean isTableOccupied(Long diningTableId, LocalDateTime newReservationStart, LocalDateTime newReservationEnd){

        List<ReservationStatus> activeStatuses = Arrays.asList(ReservationStatus.CONFIRMED, ReservationStatus.PENDING);
        List<Reservation> overlappingReservations = reservationRepository
                .findByDiningTableIdAndReservationStartLessThanAndReservationEndGreaterThanAndStatusIn(
                        diningTableId, newReservationEnd, newReservationStart, activeStatuses);


        return !overlappingReservations.isEmpty();
    }

    private ReservationResponseDto mapToReservationResponseDto(Reservation reservation) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setId(reservation.getId());
        response.setCustomerName(reservation.getCustomerName());
        response.setPhoneNumber(reservation.getPhoneNumber());
        response.setReservationStart(reservation.getReservationStart());
        response.setReservationEnd(reservation.getReservationEnd());
        response.setStatus(reservation.getStatus());

        // Əgər rezervasiyanın aid olduğu masa mövcuddursa, masa kodunu cavaba əlavə edirik.
        if (reservation.getDiningTable() != null) {
            response.setTableCode(reservation.getDiningTable().getCode());
        }
        return response;
    }
}
