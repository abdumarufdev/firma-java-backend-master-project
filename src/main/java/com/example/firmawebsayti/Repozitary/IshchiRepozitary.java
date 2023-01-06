package com.example.firmawebsayti.Repozitary;

import com.example.firmawebsayti.Entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IshchiRepozitary extends JpaRepository<Ishchi,Integer> {
    boolean existsByUsername(String username);

    Optional<Ishchi> findByUsername(String username);

    Optional<Ishchi> findByUsernameAndEmailkod(String username, String emailkod);


    Optional<Ishchi> findByIdAndBulimEntityId(Integer id, Integer bulimEntity_id);
}
