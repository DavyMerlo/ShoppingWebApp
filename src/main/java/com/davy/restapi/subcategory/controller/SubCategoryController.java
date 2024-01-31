package com.davy.restapi.subcategory.controller;

import com.davy.restapi.category.service.CategoryService;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.subcategory.request.SubCategoryRequest;
import com.davy.restapi.subcategory.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;



    @GetMapping("/{id}")
    public ResponseEntity<?> findSubCategoryById(@PathVariable(value = "id") final Long id){
        var data = subCategoryService.findSubCategoryById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{SubCatId}/category")
    public ResponseEntity<?> findCategoryBySubCategoryId(@PathVariable(value = "SubCatId")
                                                         final  Long SubCatId){
        var data = categoryService.findCategoryBySubCategoryId(SubCatId);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubCategoryById(@PathVariable(value = "id") final Long id,
                                               @RequestBody SubCategoryRequest request){
        subCategoryService.updateSubCategoryById(id, request);
        var data = subCategoryService.findSubCategoryById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategoryRequest request){
        var createdSubCategoryId = subCategoryService.saveSubCategory(request);
        var data = subCategoryService.findSubCategoryById(createdSubCategoryId);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
