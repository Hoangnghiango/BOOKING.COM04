package com.casestudy_module4.controller;

import com.casestudy_module4.dto.AccommodationDTO;
import com.casestudy_module4.dto.RoomDTO;
import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.User;
import com.casestudy_module4.service.impl.AccommodationService;
import com.casestudy_module4.service.impl.RoomService;
import com.casestudy_module4.service.impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private UserService userService;

    @GetMapping("/create/{accId}")
    public ModelAndView showFormRegisterAccommodation(@PathVariable Long accId, Model model, RoomDTO roomDTO)  {
        Accommodation accommodation = accommodationService.getAccommodationById(accId).get();
        //findRoomsByAccId
        ModelAndView modelAndView = new ModelAndView("/RoomRegister");

        List<Room> roomList = roomService.findRoomListByAccommodationId(accId);
        modelAndView.addObject("currentUser",userService.findByUsernameAfterLogin());
        modelAndView.addObject("roomList",roomList);
        modelAndView.addObject("accommodation",accommodation);
        modelAndView.addObject("roomDTO",roomDTO);
        return modelAndView;
    }
    @PostMapping("/create/{accId}")
    public ModelAndView createRoomByAccommodationId(
            @PathVariable("accId") Long accommodationId,
            @ModelAttribute RoomDTO roomDTO,
            BindingResult bindingResult,
            Model model
    ) {

        ModelAndView modelAndView;
        User user = userService.findByUsernameAfterLogin();
        if(user.equals(null)){
            modelAndView = new ModelAndView("login");
            return modelAndView;
        }
        Accommodation accommodation = accommodationService.findAccommodationById(accommodationId);
        List<Room> roomList ;
        roomList   = roomService.findRoomListByAccommodationId(accommodationId);

        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("RoomRegister");
            modelAndView.addObject("currentUser",userService.findByUsernameAfterLogin());
            modelAndView.addObject("roomList",roomList);
            modelAndView.addObject("accommodation",accommodation);
            modelAndView.addObject("roomDTO",roomDTO);
            return modelAndView;
        }
        // Create the room and associate it with the accommodation
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        room.setAccommodation(accommodation);

        // Save the room
        roomService.save(room);
        roomList   = roomService.findRoomListByAccommodationId(accommodationId);
        modelAndView = new ModelAndView("RoomRegister");
        modelAndView.addObject("currentUser",userService.findByUsernameAfterLogin());
        modelAndView.addObject("roomList",roomList);
        modelAndView.addObject("accommodation",accommodation);
        modelAndView.addObject("roomDTO",roomDTO);
        return modelAndView;
    }



}
