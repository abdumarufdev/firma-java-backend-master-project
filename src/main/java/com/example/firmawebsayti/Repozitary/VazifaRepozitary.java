package com.example.firmawebsayti.Repozitary;

import com.example.firmawebsayti.Entity.Vazifa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VazifaRepozitary  extends JpaRepository<Vazifa,Integer> {

}
