package com.example.entity.movie;

import com.example.entity.reservation.ReservedSeat;
import lombok.Getter;

import java.util.List;

@Getter
public class Seats {

    private static final int MAX_ALLOWED_SEATS = 5;

    private final List<Seat> seats;

    public Seats(List<Seat> seats) {
        this.seats = List.copyOf(seats);
    }

    public boolean isSizeExceedingLimit() {
        return this.seats.size() > MAX_ALLOWED_SEATS;
    }

    public boolean isContinuousSeat() {
        List<String> rows = createLowList();
        List<Integer> columns = createColumList();

        for (int i = 1; i < rows.size(); i++) {
            if (!rows.get(1).equals(rows.get(i))) {
                return false;
            }
        }

        for (int i = 1; i < columns.size(); i++) {
            if (columns.get(i) - columns.get(i - 1) != 1) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> createColumList() {
        return seats.stream()
                .map(Seat::getColumn)
                .sorted()
                .toList();
    }

    private List<String> createLowList() {
        return seats.stream()
                .map(Seat::getRow)
                .distinct()
                .toList();
    }

    public boolean isSeatMatch(List<Long> seatIds) {
        return this.seats.size() != seatIds.size();
    }

    public boolean isTotalSeatCountExceeding(List<ReservedSeat> reservedSeats) {
        return this.seats.size() + reservedSeats.size() > MAX_ALLOWED_SEATS;
    }
}
