package com.graynaud.eu4savedisplayerbo.repository;

import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    List<Campaign> findByAuthor(User author);

    Campaign findByName(String name);
}
