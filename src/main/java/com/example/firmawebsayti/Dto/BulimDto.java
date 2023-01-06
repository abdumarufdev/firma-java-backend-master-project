package com.example.firmawebsayti.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BulimDto {
        @NotBlank(message = "Problini olmaydi")
        private String nomi;

}
