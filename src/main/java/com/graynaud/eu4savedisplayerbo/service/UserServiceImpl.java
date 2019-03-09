package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.api.input.UserCreate;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.repository.UserRepository;
import com.graynaud.eu4savedisplayerbo.service.error.PseudoAlreadyExistsException;
import com.graynaud.eu4savedisplayerbo.service.error.SteamIdAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public User create (User user) throws PseudoAlreadyExistsException, SteamIdAlreadyExistsException {
        if (userRepository.findByPseudo(user.getPseudo()) != null) {
            throw new PseudoAlreadyExistsException("A user with pseudo " + user.getPseudo() + " already exists !");
        }

        if (userRepository.findBySteamId(user.getSteamId()) != null) {
            throw new SteamIdAlreadyExistsException("A user with steamId " + user.getSteamId() + " already exists !");
        }

        return userRepository.save(user);
    }

    @Override
    public User create (UserCreate userCreate) throws SteamIdAlreadyExistsException, PseudoAlreadyExistsException {
        User user = new User();
        user.setPseudo(userCreate.getPseudo());
        user.setSteamId(userCreate.getSteamId());

        return this.create(user);
    }

    @Override
    public User findByPseudo (String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }

    @Override
    public User findBySteamId (String steamId) {
        return userRepository.findBySteamId(steamId);
    }

    @Override
    public User findByPseudoAndSteamId (String pseudo, String steamId) {
        return userRepository.findByPseudoAndSteamId(pseudo, steamId);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll () {
        return userRepository.findAll();
    }
}
