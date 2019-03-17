package com.graynaud.eu4savedisplayerbo.service;

import com.graynaud.eu4savedisplayerbo.model.save.Eu4Save;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface SaveReadService {

    byte[] saveToJsonByteArray (Eu4Save save) throws IOException;

    Eu4Save readSaveContent (MultipartFile file, String campaignName) throws ParseException, IOException;
}
