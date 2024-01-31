package com.davy.restapi.category.controller;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.category.service.CategoryService;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.subcategory.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        var data = categoryService.findAllCategories();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(value = "id")
                                                  final Long id){
        var data = categoryService.findCategoryById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{catId}/subcategories")
    public ResponseEntity<?> findSubCategoriesByCategoryId(@PathVariable(value = "catId") final  Long catId){
        var data = subCategoryService.findSubCategoriesByCategoryId(catId);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id")
                                                    final Long id,
                                                @RequestBody CategoryUpdateRequest request){
        categoryService.updateCategoryById(id, request);
        var data = categoryService.findCategoryById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateRequest request){
        var createdCategoryId = categoryService.saveCategory(request);
        var data = categoryService.findCategoryById(createdCategoryId);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
