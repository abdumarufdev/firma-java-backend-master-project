package com.example.firmawebsayti.Dto;

import lombok.Data;

import javax.persistence.OneToOne;

@Data
public class VazifaDto {
    private String nomi;
    private String vaqti;
    private String izoh;
    private Integer bulimId;
    private Integer ishchiId;

}
