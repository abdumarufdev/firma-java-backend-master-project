package com.example.firmawebsayti.Entity;

import com.example.firmawebsayti.Entity.Abstrak.Abstrakt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vazifa extends Abstrakt {

    private String nomi;
    private String vaqti;
    private String izoh;

    @OneToOne
    private  Ishchi ishchi;
}
