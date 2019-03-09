package com.graynaud.eu4savedisplayerbo.api;

import com.graynaud.eu4savedisplayerbo.api.dto.UserDTO;
import com.graynaud.eu4savedisplayerbo.api.error.ErrorMessage;
import com.graynaud.eu4savedisplayerbo.api.input.UserCreate;
import com.graynaud.eu4savedisplayerbo.model.User;
import com.graynaud.eu4savedisplayerbo.service.UserService;
import com.graynaud.eu4savedisplayerbo.service.error.PseudoAlreadyExistsException;
import com.graynaud.eu4savedisplayerbo.service.error.SteamIdAlreadyExistsException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController (UserService userService) {this.userService = userService;}

    @GetMapping(value = "search", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser (@RequestParam(value = "pseudo", required = false) String pseudo,
                                            @RequestParam(value = "steamId", required = false) String steamId) {
        LOGGER.info("Searching a user with: pseudo: {}, steamId: {}", pseudo, steamId);

        User user;

        if (StringUtils.isNotBlank(pseudo) && StringUtils.isNotBlank(steamId)) {
            user = userService.findByPseudoAndSteamId(pseudo, steamId);
        } else if (StringUtils.isNotBlank(pseudo)) {
            user = userService.findByPseudo(pseudo);
        } else {
            user = userService.findBySteamId(steamId);
        }

        if (user == null) {
            LOGGER.info("No user found with user with: pseudo: {}, steamId: {}", pseudo, steamId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO(user);

        LOGGER.info("Found user: {}", user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers () {
        LOGGER.info("Get all users");

        List<User> users = userService.findAll();

        List<UserDTO> usersDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());

        LOGGER.info("Found users: {}", usersDTO);
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser (@Valid @RequestBody UserCreate userCreate) {
        LOGGER.info("Trying to create a user from: {}", userCreate);

        User user;

        try {
            user = userService.create(userCreate);
        } catch (PseudoAlreadyExistsException e) {
            LOGGER.error("Can't create user because: {}", e.getMessage());
            return new ResponseEntity<>(new ErrorMessage("PSEUDO_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        } catch (SteamIdAlreadyExistsException e) {
            LOGGER.error("Can't create user because: {}", e.getMessage());
            return new ResponseEntity<>(new ErrorMessage("STEAMID_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = new UserDTO(user);

        LOGGER.info("Created new user {}", userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable("id") int id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
