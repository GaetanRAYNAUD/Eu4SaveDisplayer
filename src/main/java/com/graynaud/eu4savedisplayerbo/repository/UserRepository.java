package com.graynaud.eu4savedisplayerbo.repository;

import com.graynaud.eu4savedisplayerbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPseudo(String pseudo);

    User findBySteamId (String steamId);

    User findByPseudoAndSteamId(String pseudo, String steamId);
}
