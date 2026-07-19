package com.campusmarket.repository;


import com.campusmarket.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;



public interface ChatRepository extends JpaRepository<Chat, Long> {


    List<Chat> findByUsuario1OrUsuario2(
            Long usuario1,
            Long usuario2
    );
    Optional<Chat> findByUsuario1AndUsuario2(
            Long usuario1,
            Long usuario2
    );

    Optional<Chat> findByUsuario2AndUsuario1(
            Long usuario2,
            Long usuario1
    );

}