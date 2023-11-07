package com.casestudy_module4.controller;

import com.casestudy_module4.dto.RoomDateDTO;
import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.RoomDate;
import com.casestudy_module4.model.User;
import com.casestudy_module4.service.impl.AccommodationService;
import com.casestudy_module4.service.impl.RoomDateService;
import com.casestudy_module4.service.impl.RoomService;
import com.casestudy_module4.service.impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/roomDates")
public class RoomDateController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomDateService roomDateService;
//                    <a th:href="@{/roomDates/create/{roomId}(roomId=${room.id})}"><button class="btn btn-danger w-100">Set Lịch</button></a>
    @GetMapping("/create/{roomId}")
    public ModelAndView showFormRoomDate(@PathVariable Long roomId, Model model, RoomDateDTO roomDateDTO)  {
            Room room = roomService.findRoomById(roomId);
            if(room == null){
                return new ModelAndView("/login1");
            }
            Accommodation accommodation = room.getAccommodation();
            //getRoomDateByRoomId
            List<RoomDate> roomDateList = room.getRoomDates();
            ModelAndView modelAndView = new ModelAndView("roomDateRegister");
            modelAndView.addObject("roomDateList",roomDateList);
            modelAndView.addObject("rooms",room);
            modelAndView.addObject("accommodation",accommodation);
            modelAndView.addObject("roomDateDto",roomDateDTO);

            return modelAndView;
    }
    @PostMapping("/create/{roomId}")
    public ModelAndView createRoomByAccommodationId(
            @PathVariable("roomId") Long roomId,
            @ModelAttribute RoomDateDTO RoomDateDTO,
            BindingResult bindingResult,
            Model model)
    {
        ModelAndView modelAndView;
        User user = userService.findByUsernameAfterLogin();
        if(user == null){
            modelAndView = new ModelAndView("login");
            return modelAndView;
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("roomDateDTO", RoomDateDTO);
            modelAndView = new ModelAndView("roomDateRegister");
            return modelAndView;
        }
        Room room = roomService.findRoomById(roomId);
        List<RoomDate> roomDateList = room.getRoomDates();

        RoomDate roomDate = new RoomDate();
        BeanUtils.copyProperties(RoomDateDTO, roomDate);

//        List<RoomDate> roomDateList = roomDateService.findAll();
        for(RoomDate ele : roomDateList){
            if(ele.getDate().equals(RoomDateDTO.getDate())){
                roomDateService.save(roomDate);
            }
        }
        modelAndView = new ModelAndView("roomDateRegister");
        return modelAndView;
    }

}
