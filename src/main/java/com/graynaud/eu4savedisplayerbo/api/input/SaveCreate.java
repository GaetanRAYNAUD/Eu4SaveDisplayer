package com.graynaud.eu4savedisplayerbo.api.input;

import org.springframework.web.multipart.MultipartFile;

public class SaveCreate {

    private String campaign;

    private MultipartFile file;

    public String getCampaign () {
        return campaign;
    }

    public void setCampaign (String campaign) {
        this.campaign = campaign;
    }

    public MultipartFile getFile () {
        return file;
    }

    public void setFile (MultipartFile file) {
        this.file = file;
    }
}
