package com.davy.restapi.subcategory.controller;

import com.davy.restapi.payment.request.PaymentCreateRequest;
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

    @GetMapping
    public ResponseEntity<?> findAllSubCategories(){
        var data = subCategoryService.findAllSubCategories();
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSubCategoryById(@PathVariable(value = "id") final Long id){
        var data = subCategoryService.findSubCategoryById(id);
        return ResponseHandler.generateResponse("successful",  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubCategoryById(@PathVariable(value = "id") final Long id,
                                               @RequestBody SubCategoryRequest request){
        var data = subCategoryService.updateSubCategoryById(id, request);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategoryRequest request){
        var data = subCategoryService.saveSubCategory(request);
        return ResponseHandler.generateResponse("successful", HttpStatus.CREATED, data);
    }
}
