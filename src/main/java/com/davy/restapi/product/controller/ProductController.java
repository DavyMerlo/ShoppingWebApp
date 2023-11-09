package com.davy.restapi.product.controller;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.service.ProductService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        var data = productService.findAllProducts(page, size);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") final Long id){
        var data = productService.findProductById(id);
        return ResponseHandler.generateResponse("successful",  HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request){
        var data = productService.saveProduct(request);
        return ResponseHandler.generateResponse("successful", HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable(value = "id") final Long id,
                                               @RequestBody ProductRequest request){
        var data = productService.updateProductById(id, request);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }
}
