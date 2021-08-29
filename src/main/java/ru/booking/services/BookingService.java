package ru.booking.services;

import javassist.NotFoundException;
import ru.booking.models.Booking;
import ru.booking.payload.request.BookingRequest;

import java.util.List;

public interface BookingService {
    List<Booking> getAll();
    Booking create(BookingRequest bookingRequest) throws NotFoundException;

}
