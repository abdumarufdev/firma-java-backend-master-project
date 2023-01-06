package com.example.firmawebsayti.Dto;

import com.example.firmawebsayti.Entity.Enum.Xuquqlar;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LavozimDto {
    @NotBlank(message = "Problini olmaydi")
    private String nomi;
    @ElementCollection(fetch = FetchType.LAZY)
    @NotEmpty(message = "Huquqlar bo'sh bo'lmasloigi kk")
    private List<Xuquqlar> xuquqlarList;
    @NotNull(message = "Izoh bo'sh bo'lmasin")
    private String izoh;


}
