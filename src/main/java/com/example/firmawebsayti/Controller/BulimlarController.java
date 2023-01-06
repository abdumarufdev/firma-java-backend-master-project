package com.example.firmawebsayti.Controller;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.BulimDto;
import com.example.firmawebsayti.Servise.BulimServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/BulimJoyla")
public class BulimlarController {
@Autowired
BulimServise bulimServise;


    @PreAuthorize(value = "hasAnyAuthority('ADDBULIM')")
    @PostMapping("/kiritish")
    public HttpEntity<?> Joyla(@RequestBody BulimDto bulimDto){
        AypiRespons aypiRespons=bulimServise.Joyla1(bulimDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('READBULIM')")
    @GetMapping("/uqiham")
    public HttpEntity<?> Uqishham(){
        AypiRespons aypiRespons=bulimServise.UqishHam();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETEBULIM')")
    @DeleteMapping("/deleteBulim/{id}")
    public HttpEntity<?> deleteBulim(@PathVariable Integer id){
        AypiRespons aypiRespons=bulimServise.deleteBulim(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());

    }

}
