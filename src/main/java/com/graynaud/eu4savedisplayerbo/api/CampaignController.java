package com.graynaud.eu4savedisplayerbo.api;

import com.graynaud.eu4savedisplayerbo.api.dto.CampaignDTO;
import com.graynaud.eu4savedisplayerbo.api.error.ErrorMessage;
import com.graynaud.eu4savedisplayerbo.api.input.CampaignCreate;
import com.graynaud.eu4savedisplayerbo.model.Campaign;
import com.graynaud.eu4savedisplayerbo.service.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/campaign")
@Api(value = "campaign", description = "CRUD controller for campaign.", tags = { "Campaign CRUD controller" })
public class CampaignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

    private final CampaignService campaignService;

    public CampaignController (CampaignService campaignService) {this.campaignService = campaignService;}

    @ApiOperation(value = "Search a campaign by name.", response = CampaignDTO.class)
    @GetMapping(value = "search", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignDTO> getCampaign (@RequestParam("name") String name) {
        LOGGER.info("Searching for a campaign with the name: {}", name);

        Campaign campaign = campaignService.findByName(name);

        if (campaign == null) {
            LOGGER.info("No campaign found with the name: {}", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CampaignDTO campaignDTO = new CampaignDTO(campaign);

        LOGGER.info("Found campaign with the name: {}", name);
        LOGGER.info(campaign.toString());
        return new ResponseEntity<>(campaignDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a campaign.", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, response = CampaignDTO.class)
    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity createCampaign (@Valid @RequestBody CampaignCreate campaignCreate) {
        LOGGER.info("Trying to create a campaign from: {}", campaignCreate);

        Campaign campaign;

        try {
            campaign = campaignService.create(campaignCreate);
        } catch (NotFoundException e) {
            LOGGER.error("Can't create campaign because can't find user {}", campaignCreate.getAuthor());
            return new ResponseEntity<>(new ErrorMessage("CANT_FIND_AUTHOR"), HttpStatus.BAD_REQUEST);
        }

        CampaignDTO campaignDTO = new CampaignDTO(campaign);

        LOGGER.info("Created new campaign {}", campaignDTO);
        return new ResponseEntity<>(campaignDTO, HttpStatus.CREATED);
    }
}
