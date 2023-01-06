package com.example.firmawebsayti.Repozitary;

import com.example.firmawebsayti.Entity.BulimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BulimRepozitary extends JpaRepository<BulimEntity,Integer> {

    Optional<BulimEntity> findByNomi(String nomi);
}
