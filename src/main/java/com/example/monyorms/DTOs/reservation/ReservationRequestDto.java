package com.example.monyorms.DTOs.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;





public class ReservationRequestDto {

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    @NotBlank(message = "Müştəri adı boş ola bilməz.")
    private String customerName;

    @NotBlank(message = "Telefon nömrəsi boş ola bilməz.")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Telefon nömrəsi düzgün formatda deyil.")
    private String phoneNumber;

    @NotNull(message = "Rezervasiyanın başlama vaxtı boş ola bilməz.")
    @Future(message = "Rezervasiyanın başlama vaxtı gələcəkdə olmalıdır.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reservationStart;

    @NotNull(message = "Rezervasiyanın bitmə vaxtı boş ola bilməz.")
    @Future(message = "Rezervasiyanın bitmə vaxtı gələcəkdə olmalıdır.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime reservationEnd;

    @NotNull(message = "Masa ID boş ola bilməz.")
    private Long tableId;
}
