package com.graynaud.eu4savedisplayerbo.service.error;

public class CampaignNotFoundException extends Exception {
    public CampaignNotFoundException () {
    }

    public CampaignNotFoundException (String message) {
        super(message);
    }
}
