package com.example.firmawebsayti.Entity;

import com.example.firmawebsayti.Entity.Abstrak.Abstrakt;
import com.example.firmawebsayti.Entity.Enum.Xuquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lavozim extends Abstrakt {
    //  @NotBlank(message = "Problini olmaydi")
    private String nomi;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    //   @NotEmpty(message = "Huquqlar bo'sh bo'lmasloigi kk")
    private List<Xuquqlar> xuquqlarList;
    //  @NotNull(message = "Izoh bo'sh bo'lmasin")
    @Column(nullable = false)
    private String izoh;

}
