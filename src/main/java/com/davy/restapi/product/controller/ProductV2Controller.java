package com.davy.restapi.product.controller;

import com.davy.restapi.product.dto.ProductDTO;
import com.davy.restapi.product.response.ProductListResponse;
import com.davy.restapi.product.service.ProductService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/products")
@RequiredArgsConstructor
public class ProductV2Controller {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAllProducts(){
        var response = new ProductListResponse();
        var products = productService.findAll();
        response.setProductDTOS((List<ProductDTO>) products);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }
}
