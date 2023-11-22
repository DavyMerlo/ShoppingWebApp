package com.davy.restapi.image.controller;

import com.davy.restapi.image.service.ImageService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping()
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        var data = imageService.save(file);
        return ResponseHandler.generateResponse("successful", HttpStatus.CREATED, data);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id)
            throws IOException, DataFormatException {

        var data = imageService.getById(id);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @GetMapping(path = {"actualImage/{id}"})
    public ResponseEntity<?> getActualImage(@PathVariable(value = "id") final Long id) {
        byte[] imageData = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(imageData);
    }
}
