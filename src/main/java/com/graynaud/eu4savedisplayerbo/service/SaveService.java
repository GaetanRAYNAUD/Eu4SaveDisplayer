package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.model.Save;
import com.graynaud.eu4savedisplayerbo.model.User;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SaveService {
    List<Save> findByVersion (String version);

    List<Save> findByPlayer (User player);

    List<Save> findByAuthor(User author);

    List<Save> findByCampaignOrderByDateDesc(Campaign campaign);

    List<Save> findByCampaignOrderByDateAsc(Campaign campaign);

    Save create (Save save);

    Save create (String campaign, MultipartFile file) throws NotFoundException;
}
