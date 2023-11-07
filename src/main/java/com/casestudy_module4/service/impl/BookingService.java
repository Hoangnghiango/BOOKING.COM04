package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.Booking;
import com.casestudy_module4.service.IBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookingService implements IBookingService {
    @Override
    public List<Booking> findAll() {
        return null;
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
