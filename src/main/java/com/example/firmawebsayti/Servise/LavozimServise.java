package com.example.firmawebsayti.Servise;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.LavozimDto;
import com.example.firmawebsayti.Entity.Lavozim;
import com.example.firmawebsayti.Repozitary.LavozimRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimServise {
    @Autowired
    LavozimRepozitary lavozimRepozitary;


    public AypiRespons kiritamiza(LavozimDto lavozimDto) {
        Optional<Lavozim> byNomi = lavozimRepozitary.findByNomi(lavozimDto.getNomi());
        if (byNomi.isPresent()){
            return new AypiRespons("Bunday Lavozim mavjud ",false);
        }
        Lavozim lavozim=new Lavozim();
        lavozim.setNomi(lavozimDto.getNomi());
        lavozim.setIzoh(lavozimDto.getIzoh());
        lavozim.setXuquqlarList(lavozimDto.getXuquqlarList());
        lavozimRepozitary.save(lavozim);
        return new AypiRespons("Lavozim qo'shildi",true);

    }

    public AypiRespons deleteLavozim(Integer id) {
        lavozimRepozitary.findById(id);
        return new AypiRespons("O'chirildi",true);
    }

    public AypiRespons uiqishHam() {
        List<Lavozim> all = lavozimRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
}
