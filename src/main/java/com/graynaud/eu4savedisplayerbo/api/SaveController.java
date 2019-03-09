package com.graynaud.eu4savedisplayerbo.api;

import com.graynaud.eu4savedisplayerbo.service.SaveService;
import com.graynaud.eu4savedisplayerbo.utils.SaveReadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/api/save")
@Api(value = "save", description = "Controller for save.", tags = { "Save controller" })
public class SaveController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveController.class);

    private final SaveService saveService;

    public SaveController (SaveService saveService) {this.saveService = saveService;}

    @ApiOperation(value = "Upload a save.", consumes = "multipart/form-data", response = ByteArrayOutputStream.class)
    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity createCampaign (@RequestPart("campaign") String campaignName,
                                          @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
                                          HttpServletResponse response) throws IOException, ParseException {
        LOGGER.info("Trying to upload a save from: {}", campaignName, file);

        byte[] bytes = SaveReadUtils.saveFileToJson(file);

        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.json");
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();

        LOGGER.info("Json sent !");
        return new ResponseEntity<>(HttpStatus.OK);

/*        Save save;

        try {
            save = saveService.create(campaignName, file);
        } catch (NotFoundException e) {
            LOGGER.error("Can't create save because can't find campaign {}", campaignName);
            return new ResponseEntity<>(new ErrorMessage("CANT_FIND_CAMPAIGN"), HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("Uploaded a new save {}", save);
        return new ResponseEntity<>(HttpStatus.CREATED);*/
    }
}
