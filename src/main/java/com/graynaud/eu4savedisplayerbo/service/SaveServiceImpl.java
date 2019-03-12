package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.Save;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.model.save.Eu4Save;
import com.graynaud.eu4savedisplayerbo.repository.SaveRepository;
import com.graynaud.eu4savedisplayerbo.service.error.CampaignNotFoundException;
import com.graynaud.eu4savedisplayerbo.service.error.UserNotFoundException;
import com.graynaud.eu4savedisplayerbo.utils.SaveReadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class SaveServiceImpl implements SaveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveServiceImpl.class);

    private final SaveRepository saveRepository;

    private final CampaignService campaignService;

    private final FileServiceImpl fileService;

    private final UserService userService;

    public SaveServiceImpl (SaveRepository saveRepository, CampaignService campaignService, FileServiceImpl fileServiceImpl,
                            UserService userService) {
        this.saveRepository = saveRepository;
        this.campaignService = campaignService;
        this.fileService = fileServiceImpl;
        this.userService = userService;
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
    public Save create (String campaignName, MultipartFile file, String authorName) throws IOException, ParseException, CampaignNotFoundException, UserNotFoundException {
        Save save = new Save();

        Campaign campaign = campaignService.findByName(campaignName);

        if (campaign != null) {
            save.setCampaign(campaign);
        } else {
            throw new CampaignNotFoundException("Can't find campaign by name: " + campaignName);
        }

        User author = userService.findByPseudo(authorName);

        if (author != null) {
            save.setAuthor(author);
        } else {
            throw new UserNotFoundException("Can't find user by name: " + authorName);
        }

        Eu4Save eu4Save = SaveReadUtils.readSaveContent(file);
        save.setDate(eu4Save.getDate());
        save.setVersion(eu4Save.getSaveGameVersion().toString());

        String fileName = campaign.getName() + "_" + campaign.getSaves().size() + ".json";
        save.setFile(fileService.saveToGzipFile(SaveReadUtils.saveToJsonByteArray(eu4Save), fileName));

        try {
            save = this.create(save);
        } catch (Exception e) {
            LOGGER.error("An error occured while saving the save: {}", save, e);
            fileService.deleteGzipFile(fileName);
        }

        return save;
    }
}
