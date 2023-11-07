package com.casestudy_module4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "Thiếu username")
    @Size(min = 8, max = 15,message = "Username tu 8 - 15 ki tu")
    private String username;

    @NotEmpty(message = "Thiếu password")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
//    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})", message="Password phải có chữ số, chữ in hoa và kí tự đặc biệt")
    private String password;
}
