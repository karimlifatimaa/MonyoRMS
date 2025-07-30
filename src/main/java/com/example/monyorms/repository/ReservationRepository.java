package com.example.monyorms.repository;

import com.example.monyorms.entity.Reservation;
import com.example.monyorms.entity.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     * Müəyyən bir masada (diningTableId) və verilmiş vaxt aralığı ([reservationStart, reservationEnd]) ilə
     * üst-üstə düşən, həmçinin verilmiş statuslardan birində (activeStatuses) olan rezervasiyaları tapır.
     * Bu metod masanın bron edilib-edilmədiyini yoxlamaq üçün istifadə olunur.
     *
     * @param diningTableId Masanın unikal ID-si.
     * @param reservationEnd Yeni rezervasiyanın bitmə vaxtı. Bu vaxtdan əvvəl başlayan mövcud rezervasiyalar axtarılır.
     * @param reservationStart Yeni rezervasiyanın başlama vaxtı. Bu vaxtdan sonra bitən mövcud rezervasiyalar axtarılır.
     * @param statusesToCheck Üst-üstə düşən rezervasiya üçün yoxlanılacaq statuslar siyahısı (məsələn, CONFIRMED, PENDING).
     * @return Üst-üstə düşən rezervasiyaların siyahısı.
     */
    List<Reservation> findByDiningTableIdAndReservationStartLessThanAndReservationEndGreaterThanAndStatusIn(
            Long diningTableId, LocalDateTime reservationEnd, LocalDateTime reservationStart, List<ReservationStatus> statusesToCheck);
}
