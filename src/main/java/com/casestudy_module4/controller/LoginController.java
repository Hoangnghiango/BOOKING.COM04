package com.casestudy_module4.controller;

import com.casestudy_module4.dto.LoginDTO;
import com.casestudy_module4.dto.RegisterDTO;
import com.casestudy_module4.dto.UserDTO;
import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.User;
import com.casestudy_module4.service.impl.AccommodationService;
import com.casestudy_module4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String loginForm(Model model, LoginDTO loginDTO){
        model.addAttribute("loginDTO",loginDTO);

        return "login1";
    }
//    @ModelAttribute("user")
//    public Optional<User> getUserLogin() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String username = userDetails.getUsername();
//            return userService.findByUserName(username);
//        }
//
//        return null; // or handle this case based on your requirements
//    }

    @GetMapping("/home")
    public String loadMainView(Model model, Authentication authentication){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("accommodation", new Accommodation()); // AccommodationRegisterForm
        return "index";
    }
    @PostMapping("/create-accommodation")
    public String createProduct(@ModelAttribute Accommodation accommodation, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Tìm người dùng theo username và gán vào sản phẩm
        User user = userService.findByUserName(username).get();
        accommodation.setUser(user);

        accommodationService.save(accommodation);
        return "redirect:/products";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("registerDTO",new RegisterDTO());

        return "register";
    }
//    @GetMapping("/")
//    public String showForm(LoginForm loginForm) {
//        return "login";
//    }
//
    @PostMapping("/register")
    public String validateRegisterInfo(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO,
                                       BindingResult bindingResult,
                                       Model model,
                                       RedirectAttributes redirectAttributes,
                                       HttpSession session) {
        try{
            if (bindingResult.hasErrors()) {
                model.addAttribute("registerDTO",registerDTO);
                bindingResult.toString();
                return "register";
            }
            String username = registerDTO.getUsername();
            Optional<User> user = userService.findByUserName(username);
            if(user.isPresent()){
                model.addAttribute("registerDTO",registerDTO);
                model.addAttribute("errorRegister","Your username has been registered");
                redirectAttributes.addAttribute("message","Your username has been registered");
                return "register";
            }
            registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            userService.saveAccordingRole(registerDTO,"ROLE_CUSTOMER");
            model.addAttribute("registerDTO",registerDTO);
            model.addAttribute("success","Register Successfully");
            redirectAttributes.addAttribute("message","Register Successfully");
            model.addAttribute("user", registerDTO.getUsername());
            return "redirect:/register?success";

        } catch (Exception e) {
            model.addAttribute("error","Error :Something went wrong !!!");

        }
        return "login1";
    }
//    @PostMapping("/do-lo")
//    public String validateLoginInfo(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
//                                    BindingResult bindingResult,
//                                    Model model,
//                                    RedirectAttributes redirectAttributes,
//                                    HttpSession session,
//                                    HttpServletResponse response) {
//        try{
//            session.removeAttribute("message");
//            if (bindingResult.hasErrors()) {
//                model.addAttribute("loginDTO",loginDTO);
//                bindingResult.toString();
//                return "login";
//            }
//            String username = loginDTO.getUsername();
//            List<UserDTO> userDTOList = userService.findAll();
//            for(UserDTO user : userDTOList){
//                if (username.equals(user.getUsername())) { // Sử dụng equals() thay vì == để so sánh chuỗi
//                    if(passwordEncoder.encode(loginDTO.getPassword()).equals(user.getPassword())){
//                        // Xác thực từ username và password.
//                        Authentication authentication = authenticationManager.authenticate(
//                                new UsernamePasswordAuthenticationToken(
//                                        user.getUsername(),
//                                        user.getPassword()));
//                        // Nếu không xảy ra exception tức là thông tin hợp lệ
//                        // Set thông tin authentication vào Security Context
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                        // Trả về jwt cho người dùng.
//                        String jwt = jwtService.generateTokenLogin(authentication);
//                        // Tạo cookie chứa token
//                        Cookie cookie = new Cookie("jwtToken", jwt);
//                        cookie.setHttpOnly(true); // Đảm bảo chỉ có server mới có thể truy cập cookie
//                        cookie.setPath("/"); // Đảm bảo cookie có thể được truy cập từ tất cả các path
//                        response.addCookie(cookie); // Gửi cookie về cho người dùng
//                        return "redirect:/home";
//                    } else {
//                        model.addAttribute("wrongPass", "Sai mat khau");
//                        return "login";
//                    }
//                }
//            }
//        } catch(Exception e) {
//            session.setAttribute("message","Error :Something went wrong !!!");
//        }
//
//        return "login";
//    }
    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("/403");
        return modelAndView;
    }

    @GetMapping("/not-found")
    public ModelAndView notFound() {
        ModelAndView modelAndView = new ModelAndView("/404");
        return modelAndView;
    }
    @GetMapping("/login?error=true")
    public ModelAndView notAuthen() {
        ModelAndView modelAndView = new ModelAndView("/404");
        return modelAndView;
    }

}

