package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.api.input.CampaignCreate;
import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface CampaignService {
    Campaign create(Campaign campaign);

    Campaign create(CampaignCreate campaignCreate) throws NotFoundException;

    List<Campaign> findByAuthor(User author);

    Campaign findByName(String name);
}
