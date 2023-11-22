package com.davy.restapi.image.service;

import com.davy.restapi.image.response.ImageUploadResponse;
import com.davy.restapi.image.utils.ImageUtils;
import com.davy.restapi.image.entities.Image;
import com.davy.restapi.image.repository.ImageRepository;
import com.davy.restapi.image.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageUploadResponse save(MultipartFile file) throws IOException {
        var response = new ImageUploadResponse();
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());
        response.setFileName(file.getOriginalFilename());
        return response;
    }

    @Override
    public ImageResponse getById(Long id) throws DataFormatException, IOException {
        var response = new ImageResponse();
        var image = imageRepository.findById(id);
        response.setImage(ImageUtils.decompressImage(image.get().getImageData()));
        return response;
    }

    //This was just for testing out...
    public byte[] downloadImage(Long id) {
        Optional<Image> dbImage = imageRepository.findById(id);
        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image ID",  image.getId())
                        .addContextValue("Image name", image.getName());
            }
        }).orElse(null);
    }
}
