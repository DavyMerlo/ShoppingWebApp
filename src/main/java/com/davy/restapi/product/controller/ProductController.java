package com.davy.restapi.product.controller;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.shared.service.ProductCatalogFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCatalogFacadeService productCatalogFacadeService;

    @GetMapping()
    public ResponseEntity<?> filterAndSearchProductsByNamePageable(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "subcategory", required = false) Long subCategoryId,
            @RequestParam(name = "page", defaultValue = "0") int page){
        var data = productCatalogFacadeService.filterAndSearchProductsByNamePageable(categoryId, subCategoryId, search, page);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") final Long id){
        var data = productCatalogFacadeService.findProductById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest request){
        var data = productCatalogFacadeService.saveProduct(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable(value = "id") final Long id,
                                               @RequestBody ProductRequest request){
        var data = productCatalogFacadeService.updateProductById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
