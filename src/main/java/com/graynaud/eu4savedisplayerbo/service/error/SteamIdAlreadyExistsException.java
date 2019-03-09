package com.graynaud.eu4savedisplayerbo.service.error;

public class SteamIdAlreadyExistsException extends Throwable {
    public SteamIdAlreadyExistsException()
    {super();}

    public  SteamIdAlreadyExistsException(String msg)
    {super(msg);}
}
