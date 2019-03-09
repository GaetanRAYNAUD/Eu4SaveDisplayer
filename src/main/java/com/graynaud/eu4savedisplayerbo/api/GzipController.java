package com.graynaud.eu4savedisplayerbo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.GZIPOutputStream;

@Controller
@RequestMapping("/api/gzip")
@Api(value = "gzip", description = "Save input manager. Clean up, remove useless informations and compress saves.", tags = { "Save manager" })
public class GzipController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GzipController.class);

    @ApiOperation(value = "Take a save, remove useless information, compress then return the file as a gzip.", response = ByteArrayOutputStream.class)
    @PostMapping(value = "/test", consumes = "multipart/form-data", produces = "application/gzip")
    public ResponseEntity gzipTest (@RequestBody MultipartFile file, HttpServletResponse response) {
        LOGGER.info("Trying to compress file {} ({}).", file.getOriginalFilename(), FileUtils.byteCountToDisplaySize(file.getSize()));

        String outputFileName = StringUtils.defaultIfBlank(file.getOriginalFilename(), file.getName()) + ".gz";

        try (BufferedReader input = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedOutputStream output = new BufferedOutputStream(new GZIPOutputStream(outputStream));
            int buffer;

            while ((buffer = input.read()) != -1) {
                output.write(buffer);
            }

            input.close();
            output.flush();
            output.close();

            response.setContentType("application/gzip");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + outputFileName);
            response.setContentLength(outputStream.size());
            response.getOutputStream().write(outputStream.toByteArray());

            outputStream.flush();
            outputStream.close();
            response.getOutputStream().flush();

            LOGGER.info("File is now compressed to {} !", FileUtils.byteCountToDisplaySize(outputStream.size()));
        } catch (IOException e) {
            LOGGER.error("An error occurred while compressing {} !", file.getOriginalFilename());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
