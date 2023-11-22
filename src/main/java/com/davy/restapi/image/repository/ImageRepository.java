package com.davy.restapi.image.repository;

import com.davy.restapi.image.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
