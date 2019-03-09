package com.graynaud.eu4savedisplayerbo.repository;

import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.Save;
import com.graynaud.eu4savedisplayerbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveRepository extends JpaRepository<Save, Integer> {
    List<Save> findByVersion(String version);

    List<Save> findByPlayers(User player);

    List<Save> findByAuthor(User author);

    List<Save> findByCampaignOrderByDateDesc(Campaign campaign);

    List<Save> findByCampaignOrderByDateAsc(Campaign campaign);
}
