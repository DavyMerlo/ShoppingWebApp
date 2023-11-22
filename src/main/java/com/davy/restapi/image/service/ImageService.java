package com.davy.restapi.image.service;

import com.davy.restapi.image.response.ImageUploadResponse;
import com.davy.restapi.image.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

public interface ImageService {
    ImageUploadResponse save(MultipartFile file) throws IOException;
    ImageResponse getById(Long id) throws DataFormatException, IOException;
    byte[] downloadImage(Long id);
}
