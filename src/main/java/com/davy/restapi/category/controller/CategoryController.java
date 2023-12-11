package com.davy.restapi.category.controller;

import com.davy.restapi.category.request.CategoryCreateRequest;
import com.davy.restapi.category.request.CategoryUpdateRequest;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.shared.service.ProductCatalogFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductCatalogFacadeService productCatalogFacadeService;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        var data = productCatalogFacadeService.findAllCategories();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable(value = "id")
                                                  final Long id){
        var data = productCatalogFacadeService.findCategoryById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/subcategories/{SubCatId}")
    public ResponseEntity<?> findCategoryBySubCategoryId(@PathVariable(value = "SubCatId")
                                                             final  Long SubCatId){
        var data = productCatalogFacadeService.findCategoryBySubCategoryId(SubCatId);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable(value = "id")
                                                    final Long id,
                                                @RequestBody CategoryUpdateRequest request){
        var data = productCatalogFacadeService.updateCategoryById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateRequest request){
        var data = productCatalogFacadeService.saveCategory(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
