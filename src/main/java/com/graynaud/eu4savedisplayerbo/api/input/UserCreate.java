package com.graynaud.eu4savedisplayerbo.api.input;

import javax.validation.constraints.NotBlank;

public class UserCreate {

    @NotBlank
    private String pseudo;

    private String steamId;

    public String getPseudo () {
        return pseudo;
    }

    public void setPseudo (String pseudo) {
        this.pseudo = pseudo;
    }

    public String getSteamId () {
        return steamId;
    }

    public void setSteamId (String steamId) {
        this.steamId = steamId;
    }
}
