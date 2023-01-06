package com.example.firmawebsayti.Security;

import com.example.firmawebsayti.Entity.Ishchi;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class KimYozganiniBilish implements AuditorAware<Integer> {
   @Override

public Optional<Integer> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication!=null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
        Ishchi staff = (Ishchi) authentication.getPrincipal();
        return Optional.of(staff.getId());
    }
    return Optional.empty();
}
}
