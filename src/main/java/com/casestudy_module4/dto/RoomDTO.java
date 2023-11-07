package com.casestudy_module4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @NotEmpty(message = "Not Empty ")
    private String room_Type;

    @NotEmpty(message = "Not Empty ")
    private String room_name;

    @NotEmpty(message = "Not Empty ")
    private String bed_infor;

    @NotEmpty(message = "Not Empty ")
    private String img_path01;

    @NotEmpty(message = "Not Empty ")
    private String img_path02;

    @NotEmpty(message = "Not Empty ")
    private String img_path03;

    @NotEmpty(message = "Not Empty ")
    private double price;

    @NotEmpty(message = "Not Empty ")
    private int quantity;

    @NotEmpty(message = "Not Empty ")
    private int MAXPERSON;
}
