package com.davy.restapi.subcategory.controller;

import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.shared.service.ProductCatalogFacadeService;
import com.davy.restapi.subcategory.request.SubCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final ProductCatalogFacadeService productCatalogFacadeService;

    @GetMapping()
    public ResponseEntity<?> findSubCategoriesByCategoryId(@RequestParam(name = "category",
            required = false) Long category){
        var data = productCatalogFacadeService.findSubCategoriesByCategoryId(category);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSubCategoryById(@PathVariable(value = "id") final Long id){
        var data = productCatalogFacadeService.findSubCategoryById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubCategoryById(@PathVariable(value = "id") final Long id,
                                               @RequestBody SubCategoryRequest request){
        var data = productCatalogFacadeService.updateSubCategoryById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategoryRequest request){
        var data = productCatalogFacadeService.saveSubCategory(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
