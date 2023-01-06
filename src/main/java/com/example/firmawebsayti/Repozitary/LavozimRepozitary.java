package com.example.firmawebsayti.Repozitary;

import com.example.firmawebsayti.Entity.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LavozimRepozitary extends JpaRepository<Lavozim,Integer> {
    Optional<Lavozim> findByNomi(String nomi);
    boolean existsByNomi(String nomi);

}
