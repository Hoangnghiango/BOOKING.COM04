package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.User;

import java.util.List;
import java.util.Optional;

public interface IAccommodationService {
    List<Accommodation> findAll();
    Accommodation save(Accommodation accommodation);
    Accommodation update(Accommodation accommodation);
    void deleteById(Long id);

    public Optional<Accommodation> getAccommodationById(Long id) ;
    List<Accommodation> findAccommodationListByUser(User user);
    Accommodation findAccommodationById(Long accommodationId);

    Accommodation findAccommodationByRoom(Room room);



}
