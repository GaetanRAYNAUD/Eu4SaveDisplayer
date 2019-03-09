package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.Save;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.repository.SaveRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class SaveServiceImpl implements SaveService {

    private final SaveRepository saveRepository;

    private final CampaignService campaignService;

    public SaveServiceImpl (SaveRepository saveRepository, CampaignService campaignService) {this.saveRepository = saveRepository;
        this.campaignService = campaignService;
    }

    @Override
    public List<Save> findByVersion (String version) {
        return saveRepository.findByVersion(version);
    }

    @Override
    public List<Save> findByPlayer (User player) {
        return saveRepository.findByPlayers(player);
    }

    @Override
    public List<Save> findByAuthor (User author) {
        return saveRepository.findByAuthor(author);
    }

    @Override
    public List<Save> findByCampaignOrderByDateDesc (Campaign campaign) {
        return saveRepository.findByCampaignOrderByDateDesc(campaign);
    }

    @Override
    public List<Save> findByCampaignOrderByDateAsc (Campaign campaign) {
        return saveRepository.findByCampaignOrderByDateAsc(campaign);
    }

    @Override
    public Save create (Save save) {
        return saveRepository.save(save);
    }

    @Override
    public Save create (String campaignName, MultipartFile file) throws NotFoundException {
        Save save = new Save();
        save.setFile(file.getOriginalFilename());

        Campaign campaign = campaignService.findByName(campaignName);

        if (campaign != null) {
            save.setCampaign(campaign);
        } else {
            throw new NotFoundException("Can't find campaign by name: " + campaignName);
        }

        return this.create(save);
    }
}
