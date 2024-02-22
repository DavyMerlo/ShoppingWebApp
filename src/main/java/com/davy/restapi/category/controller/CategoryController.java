package com.davy.restapi.category.controller;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.response.CategoryListResponse;
import com.davy.restapi.category.response.CategoryDetailResponse;
import com.davy.restapi.category.response.CategoryResponse;
import com.davy.restapi.category.service.CategoryService;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.subcategory.response.SubCategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(value = "id")
                                              final Long id){
        var response = new CategoryDetailResponse();
        var category = categoryService.findById(id);
        response.setCategory((CategoryTryDetailsDTO) category);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        var response = new CategoryListResponse();
        var categories = categoryService.findAll();
        response.setCategories((List<CategoryDTO>) categories);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id") final Long id,
                                                @RequestBody CategoryRequestDTO request){
        var response = new CategoryDetailResponse();
        categoryService.updateById(id, request);
        var category = categoryService.findById(id);
        response.setCategory((CategoryTryDetailsDTO) category);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<?> findSubCategoriesByCategoryId(
            @PathVariable(value = "categoryId") final  Long categoryId){
        var response = new SubCategoryListResponse();
        var subCategories = categoryService.subCategoriesByCategoryId(categoryId);
        response.setSubCategories(subCategories);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @GetMapping("/subcategories/{subCategoryId}")
    public ResponseEntity<?> findCategoryBySubCategoryId(@PathVariable(value = "subCategoryId")
                                                         final  Long subCategoryId){
        var response = new CategoryResponse();
        var category = categoryService.categoryBySubCategoryId(subCategoryId);
        response.setCategoryDTO(category);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDTO request){
        var response = new CategoryDetailResponse();
        var category = categoryService.save(request);
        response.setCategory((CategoryTryDetailsDTO) category);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }
}
