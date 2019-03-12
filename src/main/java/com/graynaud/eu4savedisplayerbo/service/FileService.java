package com.graynaud.eu4savedisplayerbo.service;

import java.io.IOException;

public interface FileService {
    String saveToGzipFile (byte[] bytes, String filename) throws IOException;

    boolean deleteGzipFile (String fileName);
}
