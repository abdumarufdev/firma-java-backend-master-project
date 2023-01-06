package com.example.firmawebsayti.Servise;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.VazifaDto;
import com.example.firmawebsayti.Entity.Ishchi;
import com.example.firmawebsayti.Entity.Vazifa;
import com.example.firmawebsayti.Repozitary.IshchiRepozitary;
import com.example.firmawebsayti.Repozitary.VazifaRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VazifaServise {
    @Autowired
    VazifaRepozitary vazifaRepozitary;

    @Autowired
    JavaMailSender getJavaMailSender;
    @Autowired
    IshchiRepozitary ishchiRepozitary;


    public AypiRespons VazifaJoy(VazifaDto vazifaDto) {
        Optional<Ishchi> byIdAndBulimEntityId = ishchiRepozitary.findByIdAndBulimEntityId( vazifaDto.getIshchiId(),vazifaDto.getBulimId());
        if (byIdAndBulimEntityId.isPresent()){
            Ishchi ishchi=byIdAndBulimEntityId.get();
            Vazifa vazifa=new Vazifa();
            vazifa.setNomi(vazifaDto.getNomi());
            vazifa.setIzoh(vazifaDto.getIzoh());
            vazifa.setVaqti(vazifaDto.getVaqti());
//            Optional<Ishchi> byId = ishchiRepozitary.findById(vazifaDto.getIshchiId());
//            if (byId.isPresent()){
//                return new AypiRespons("Bunday faydalanuvci topilmadi",false);
//            }
            vazifa.setIshchi(ishchiRepozitary.findById(vazifaDto.getBulimId()).get());
            boolean xabaryuborish = Xabaryuborish(ishchi.getUsername());
            if (xabaryuborish){
                vazifaRepozitary.save(vazifa);
                return new AypiRespons("ma'lumot emailgiz hisobinggizga  yuborildi",true);
            }
        }
        return new AypiRespons("ma'lumotlarni tekshirib qaytada urunib ko'ring",false);
    }

    private boolean Xabaryuborish(String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("farruxzaitov7176@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirmation email");
            mailMessage.setText("");
            getJavaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }

    public AypiRespons deleteVazifa(Integer id) {
        vazifaRepozitary.findById(id);
        return new AypiRespons("O'chirildi",true);
    }

    public AypiRespons UqishHamVaz() {
        List<Vazifa> all = vazifaRepozitary.findAll();
        return new AypiRespons(all.toString(),true);
    }
}
