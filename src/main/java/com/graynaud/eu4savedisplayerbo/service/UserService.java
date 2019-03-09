package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.api.input.UserCreate;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.service.error.PseudoAlreadyExistsException;
import com.graynaud.eu4savedisplayerbo.service.error.SteamIdAlreadyExistsException;

import java.util.List;

public interface UserService {
    User create (User user) throws PseudoAlreadyExistsException, SteamIdAlreadyExistsException;

    User create (UserCreate userCreate) throws SteamIdAlreadyExistsException, PseudoAlreadyExistsException;

    User findByPseudo (String pseudo);

    User findBySteamId (String steamId);

    User findByPseudoAndSteamId(String pseudo, String steamId);

    void deleteById (int id);

    List<User> findAll ();
}
