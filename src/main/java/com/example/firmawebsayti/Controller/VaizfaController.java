package com.example.firmawebsayti.Controller;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.VazifaDto;
import com.example.firmawebsayti.Servise.VazifaServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/VazifaJoylash")
public class VaizfaController {
    @Autowired
    VazifaServise vazifaServise;

    @PostMapping("/JoylaVaz")
    public HttpEntity<?> JoylaVazifa(@RequestBody VazifaDto vazifaDto){
        AypiRespons aypiRespons=vazifaServise.VazifaJoy(vazifaDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @DeleteMapping("/deleteVazifa")
    public HttpEntity<?> deleteVazifa(@PathVariable Integer id){
        AypiRespons aypiRespons=vazifaServise.deleteVazifa(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @GetMapping("/uqishHamVazifa")
    public HttpEntity<?> UqishHamVazifa(){
        AypiRespons aypiRespons=vazifaServise.UqishHamVaz();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());

    }

}
