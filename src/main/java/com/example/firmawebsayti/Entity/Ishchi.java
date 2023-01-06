package com.example.firmawebsayti.Entity;

import com.example.firmawebsayti.Entity.Abstrak.Abstrakt;
import com.example.firmawebsayti.Entity.Enum.Xuquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Ishchi extends Abstrakt implements UserDetails {

    @Column(nullable = false)
    private String ismi;
    @Column(nullable = false)
    private String familyasi;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String emailkod;

    @ManyToOne
    private Lavozim lavozim;

    @OneToOne
    private BulimEntity bulimEntity;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    // foydalanuvchini lovozimga xuquq;arni birlashtiradi
        List<Xuquqlar> xuquqlarList = this.lavozim.getXuquqlarList();
        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Xuquqlar xuquqlar : xuquqlarList){
            grantedAuthorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {return xuquqlar.name();}
            });
        }
        return grantedAuthorities;
    }
    public Ishchi(String ismi, String familyasi, String username, String password, Lavozim ishchiEntity, boolean enabled) {
        this.ismi = ismi;
        this.familyasi = familyasi;
        this.username = username;
        this.password = password;
        this.lavozim = ishchiEntity;
        this.enabled = enabled;
    }
}
