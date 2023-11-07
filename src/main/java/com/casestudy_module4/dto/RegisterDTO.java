package com.casestudy_module4.dto;

import com.casestudy_module4.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "Thiếu username")
    @Size(min = 8, max = 15,message = "Username tu 8 - 15 ki tu")
    private String username;

    @NotEmpty(message = "Thiếu password")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
//    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})", message="Password phải có chữ số, chữ in hoa và kí tự đặc biệt")
    private String password;

    @Email(message = "Email không hợp lệ")
    @NotEmpty(message = "Thiếu email")
    private String email;
    //    @DateTimeFormat(pattern="MM/dd/yyyy")
//    @NotNull @Past @Future
}
