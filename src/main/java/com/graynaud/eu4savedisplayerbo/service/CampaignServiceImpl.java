package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.api.input.CampaignCreate;
import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.repository.CampaignRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    private final UserService userService;

    public CampaignServiceImpl (CampaignRepository campaignRepository, UserService userService) {this.campaignRepository = campaignRepository;
        this.userService = userService;
    }

    @Override
    public Campaign create (Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign create (CampaignCreate campaignCreate) throws NotFoundException {
        Campaign campaign = new Campaign();
        campaign.setName(campaignCreate.getName());

        User user = userService.findByPseudo(campaignCreate.getAuthor());

        if (user != null) {
            campaign.setAuthor(user);
        } else {
            throw new NotFoundException("Can't find user by name: " + campaignCreate.getAuthor());
        }

        return this.create(campaign);
    }

    @Override
    public List<Campaign> findByAuthor (User author) {
        return campaignRepository.findByAuthor(author);
    }

    @Override
    public Campaign findByName (String name) {
        return campaignRepository.findByName(name);
    }
}
