package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> findAll();
    Room save(Room room);
    Room update(Room room);
    void deleteById(Long id);
    List<Room> findRoomListByAccommodation(Accommodation accommodation);

    List<Room> findRoomListByAccommodationId(Long accId);

    Room findRoomById(Long id);
}
