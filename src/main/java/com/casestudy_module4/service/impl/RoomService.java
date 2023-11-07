package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.repository.RoomRepository;
import com.casestudy_module4.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoomService  implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> findAll() {
        return null;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Room> findRoomListByAccommodation(Accommodation accommodation) {
        return roomRepository.findByAccommodation(accommodation);
    }

    @Override
    public List<Room> findRoomListByAccommodationId(Long accId) {
        return roomRepository.findByAccommodationId(accId);
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Accommodation not found with ID: " + id));
    }
}
