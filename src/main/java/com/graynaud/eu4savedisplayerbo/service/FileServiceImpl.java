package com.graynaud.eu4savedisplayerbo.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Service
public class FileServiceImpl implements FileService {
    private static final String GZIP_EXT = ".gz";
    @Value("${application.save.path}")
    private String SAVE_PATH;

    @Override
    public String saveToGzipFile (byte[] data, String fileName) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.close();

        byte[] compressed = bos.toByteArray();
        bos.close();

        String filePath = SAVE_PATH + fileName + GZIP_EXT;
        FileUtils.writeByteArrayToFile(new File(filePath), compressed);

        return filePath;
    }

    @Override
    public boolean deleteGzipFile (String fileName) {
        return FileUtils.deleteQuietly(new File(SAVE_PATH + fileName + GZIP_EXT));
    }
}
