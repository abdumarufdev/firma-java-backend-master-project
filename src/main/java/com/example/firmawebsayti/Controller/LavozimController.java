package com.example.firmawebsayti.Controller;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.LavozimDto;
import com.example.firmawebsayti.Servise.LavozimServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kiritish")
public class LavozimController {
    @Autowired
    LavozimServise lavozimServise;
    @PreAuthorize(value = "hasAnyAuthority('ADDLOVOZIM')")
    @PostMapping("/kiritamiz")
    public HttpEntity<?> Kiritish(@RequestBody LavozimDto lavozimDto){
        AypiRespons aypiRespons=lavozimServise.kiritamiza(lavozimDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETELOVOZIM')")
    @DeleteMapping("/deleteLavozim/{id}")
    public HttpEntity<?> deleteLavozim(@PathVariable Integer id){
        AypiRespons aypiRespons=lavozimServise.deleteLavozim(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('RIDLOVOZIM')")
    @GetMapping("/uiqishHam")
    public HttpEntity<?> uiqishHam(){
        AypiRespons aypiRespons=lavozimServise.uiqishHam();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
}
