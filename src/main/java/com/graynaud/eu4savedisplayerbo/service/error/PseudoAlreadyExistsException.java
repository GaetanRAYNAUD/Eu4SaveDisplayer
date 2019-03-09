package com.graynaud.eu4savedisplayerbo.service.error;

import com.mchange.util.AlreadyExistsException;

public class PseudoAlreadyExistsException extends AlreadyExistsException {
    public PseudoAlreadyExistsException()
    {super();}

    public  PseudoAlreadyExistsException(String msg)
    {super(msg);}
}
