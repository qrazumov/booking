package ru.booking.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.booking.exception.BookingException;
import ru.booking.models.Booking;
import ru.booking.payload.request.BookingRequest;
import ru.booking.services.Impl.BookingServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping("/booking")
    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    @PostMapping("/booking")
    public Booking create(@Valid @RequestBody BookingRequest bookingRequest) throws BookingException, NotFoundException {
        return bookingService.create(bookingRequest);
    }


}
