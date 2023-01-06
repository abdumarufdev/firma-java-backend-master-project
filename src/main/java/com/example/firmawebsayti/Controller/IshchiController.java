package com.example.firmawebsayti.Controller;

import com.example.firmawebsayti.Dto.AypiRespons;
import com.example.firmawebsayti.Dto.IshchiDto;
import com.example.firmawebsayti.Servise.IshchiServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Joylaymiz")
public class IshchiController {
@Autowired
    IshchiServise ishchiServise;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/Registration")
    public HttpEntity<?> Registrtion(@RequestBody IshchiDto ishchiDto){
    AypiRespons aypiRespons= ishchiServise.registration(ishchiDto);
    return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
 }
    @PostMapping("/login")
    public HttpEntity<?> Login(@RequestBody IshchiDto ishchiDto){
        AypiRespons apiResponse = ishchiServise.login(ishchiDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/emailtasdiqlash")
    public HttpEntity<?> Tasdiqlash(@RequestParam String useremail, @RequestParam String emailkod){
        AypiRespons apiResponse = ishchiServise.faollashtirish(useremail,emailkod);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('ADDISH')")
    @PostMapping("/kiritish")
    public HttpEntity<?> kiritamiz(@Valid @RequestBody IshchiDto ishchiDto){
        AypiRespons aypiRespons=ishchiServise.JoylashLavizimlarmi(ishchiDto);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETEISH')")
    @DeleteMapping("/deleteIshchi/{id}")
    public HttpEntity<?> deleteIshchi(@PathVariable Integer id){
    AypiRespons aypiRespons=ishchiServise.deleteIshchi(id);
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());
    }
    @PreAuthorize(value = "hasAnyAuthority('REDISH')")
    @GetMapping("/uqishHam")
    public HttpEntity<?> uqishHam(){
    AypiRespons aypiRespons=ishchiServise.uqishHam();
        return ResponseEntity.status(aypiRespons.isHolat()?200:208).body(aypiRespons.getXabar());

    }
}
