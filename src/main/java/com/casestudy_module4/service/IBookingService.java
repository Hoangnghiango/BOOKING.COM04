package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Booking;

import java.util.List;

public interface IBookingService {
    List<Booking> findAll();
    Booking save(Booking booking);
    Booking update(Booking booking);
    void deleteById(Long id);
}
