package com.davy.restapi.subcategory.controller;

import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.subcategory.dto.SubCategoryDetailDTO;
import com.davy.restapi.subcategory.dto.SubCategoryRequestDTO;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import com.davy.restapi.subcategory.response.SubCategoryResponse;
import com.davy.restapi.subcategory.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<?> findAllSubCategories(){
        var response = new SubCategoryListResponse();
        var subCategories = subCategoryService.findAll();
        response.setSubCategories((List<Object>) subCategories);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSubCategoryById(@PathVariable(value = "id") final Long id){
        var response = new SubCategoryResponse();
        var subCategory = subCategoryService.findById(id);
        response.setSubCategory((SubCategoryDetailDTO) subCategory);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubCategoryById(@PathVariable(value = "id") final Long id,
                                               @RequestBody SubCategoryRequestDTO request){
        var response = new SubCategoryResponse();
        subCategoryService.updateById(id, request);
        var subCategory = subCategoryService.findById(id);
        response.setSubCategory((SubCategoryDetailDTO) subCategory);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PostMapping
    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategoryRequestDTO request){
        var response = new SubCategoryResponse();
        var subCategory = subCategoryService.save(request);
        response.setSubCategory((SubCategoryDetailDTO) subCategory);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }
}
