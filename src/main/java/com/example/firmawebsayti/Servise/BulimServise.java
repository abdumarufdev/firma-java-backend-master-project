package com.example.firmawebsayti.Servise;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.BulimDto;
import com.example.firmawebsayti.Entity.BulimEntity;
import com.example.firmawebsayti.Repozitary.BulimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BulimServise {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BulimRepozitary bulimRepozitary;



    public AypiRespons Joyla1(BulimDto bulimDto) {
        Optional<BulimEntity> byNomi = bulimRepozitary.findByNomi(bulimDto.getNomi());
        if (byNomi.isPresent()){
            return new AypiRespons("Bunday Lavozim mavjud",false);
        }
        BulimEntity bulimEntity=new BulimEntity();
        bulimEntity.setNomi(bulimDto.getNomi());
        bulimRepozitary.save(bulimEntity);
        return new AypiRespons("Bulim qo'shildi",true);

    }

    public AypiRespons UqishHam() {
        List<BulimEntity> all = bulimRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }


    public AypiRespons deleteBulim(Integer id) {
        bulimRepozitary.findById(id);
        return new AypiRespons("O'chirildi",true);
    }
}
