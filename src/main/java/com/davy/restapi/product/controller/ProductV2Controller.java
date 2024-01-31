package com.davy.restapi.product.controller;

import com.davy.restapi.product.service.ProductService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/products")
@RequiredArgsConstructor
public class ProductV2Controller {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAllProducts(){
        var products = productService.findAllProducts();
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, products);
    }
}
