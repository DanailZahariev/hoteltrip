package bg.hoteltrip.model.dto;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SearchAvailabilityHotelDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter a date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter a date")
    private LocalDate endDate;

    public SearchAvailabilityHotelDTO() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public SearchAvailabilityHotelDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SearchAvailabilityHotelDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }
}
