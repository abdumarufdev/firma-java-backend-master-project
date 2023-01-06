package com.example.firmawebsayti.Entity.Abstrak;

import com.example.firmawebsayti.Entity.Ishchi;
import com.example.firmawebsayti.Entity.Enum.Xuquqlar;
import com.example.firmawebsayti.Entity.Lavozim;
import com.example.firmawebsayti.Repozitary.IshchiRepozitary;
import com.example.firmawebsayti.Repozitary.LavozimRepozitary;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.firmawebsayti.Entity.Enum.Xuquqlar.*;

@Data
@Component
public class BoshlangichYuklanish implements CommandLineRunner {
    @Value(value = "${spring.sql.init.mode}")
    String holatt;
    @Autowired
    IshchiRepozitary ishchiRepozitary;

    @Autowired
    LavozimRepozitary lavozimRepozitary;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (holatt.equals("always")){
            Xuquqlar[] xuquqlars=Xuquqlar.values();
            Lavozim admin = lavozimRepozitary.save(new Lavozim((String) IshchiConsdata.ADMIN,Arrays.asList(xuquqlars),"admin"));
            Lavozim user = lavozimRepozitary.save(new Lavozim(
                    IshchiConsdata.USER, Arrays.asList(ADDISH, REDISH, EDITISH, DALETEISH), "User"));

            ishchiRepozitary.save(new Ishchi(
                    "Admin","asdfg","farrux@gmail.com",passwordEncoder.encode("farrux2003"),admin,true
            ));
            ishchiRepozitary.save(new Ishchi(
                    "farrux","zaitov","dfghj@gmail.com",passwordEncoder.encode("farrux2003"),user,true
            ));
        }
    }
}
