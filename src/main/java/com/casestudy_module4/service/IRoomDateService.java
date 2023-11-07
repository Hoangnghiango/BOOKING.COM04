package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.RoomDate;

import java.util.List;

public interface IRoomDateService {
    List<RoomDate> findAll();
    RoomDate save(RoomDate RoomDate);
    RoomDate update(RoomDate RoomDate);
    void deleteById(Long id);
}
