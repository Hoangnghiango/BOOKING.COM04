package com.casestudy_module4.controller;

import com.casestudy_module4.dto.AccommodationDTO;
import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.User;
import com.casestudy_module4.service.impl.AccommodationService;
import com.casestudy_module4.service.impl.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/accommodation")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private UserService userService;
//
//    @ModelAttribute("user")
//    public User getLoginUser() { return userService.findByUsernameAfterLogin();}

    @GetMapping("")
    public ModelAndView showFormRegisterAccommodation(Model model, AccommodationDTO accommodationDTO) {
        User currentUser = userService.findByUsernameAfterLogin(); //
        model.addAttribute("currentUser",userService.findByUsernameAfterLogin());
        List<Accommodation> accommodations = accommodationService.findAccommodationListByUser(currentUser);
        ModelAndView modelAndView = new ModelAndView("accommodation/AccommodationRegisterForm");
        modelAndView.addObject("accommodationDTO",accommodationDTO);
        modelAndView.addObject("accommodationList", accommodations);
        return modelAndView;
    }
    @PostMapping("")
    public String createNewAccommodation(@Valid @ModelAttribute AccommodationDTO accommodationDTO, BindingResult bindingResult,
                                    Model model, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accommodationDTO", accommodationDTO);
            return "accommodation/AccommodationRegisterForm";
        }
        Accommodation accommodation = new Accommodation();
        BeanUtils.copyProperties(accommodationDTO, accommodation);
        String userName = principal.getName();
        User user = userService.findByUserName(userName).get();
        accommodation.setUser(user);
        accommodationService.save(accommodation);
        model.addAttribute("success","Taọ mới chỗ nghỉ của bạn thành công !!!");
        model.addAttribute("accnew", accommodation.getName());
        redirectAttributes.addFlashAttribute("message", "Create successfull");
        return "redirect:/accommodation?success";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditAccommodation(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        ModelAndView modelAndView;
        if (accommodationService.getAccommodationById(id) == null) {
            redirectAttributes.addFlashAttribute("message", "Find not Found id");
             modelAndView = new ModelAndView("/RoomRegister");
        } else {
            Accommodation accommodation = accommodationService.findAccommodationById(id);
             modelAndView = new ModelAndView("accommodation/editAccommodation");
            AccommodationDTO accommodationDTO = new AccommodationDTO();
            BeanUtils.copyProperties(accommodationService.getAccommodationById(id),accommodationDTO);
            modelAndView.addObject("id",id);
            modelAndView.addObject("accommodationDTO", accommodationDTO);
            modelAndView.addObject("accommodation", accommodation);
            return modelAndView;

        }
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editAccommodation(@PathVariable Long id, @ModelAttribute @Valid AccommodationDTO accommodationDTO,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Accommodation existingAccommodation = accommodationService.getAccommodationById(id).get();
        // Update the existingAccommodation with the values from accommodationDTO
        existingAccommodation.setName(accommodationDTO.getName());
        existingAccommodation.setType(accommodationDTO.getType());
        existingAccommodation.setStar(accommodationDTO.getStar());
        existingAccommodation.setDescription(accommodationDTO.getDescription());
        existingAccommodation.setAddress(accommodationDTO.getAddress());
        existingAccommodation.setCity(accommodationDTO.getCity());
        existingAccommodation.setCountry(accommodationDTO.getCountry());

        // Save the updated accommodation
        accommodationService.save(existingAccommodation);
        return "redirect:/accommodation";

    }

}
