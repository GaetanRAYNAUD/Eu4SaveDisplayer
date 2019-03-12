package com.graynaud.eu4savedisplayerbo.service.error;

public class UserNotFoundException extends Exception {
    public UserNotFoundException () {
        super();
    }

    public UserNotFoundException (String message) {
        super(message);
    }
}
