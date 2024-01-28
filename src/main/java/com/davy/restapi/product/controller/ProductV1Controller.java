package com.davy.restapi.product.controller;

import com.davy.restapi.product.request.ProductRequest;
import com.davy.restapi.product.service.ProductService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductV1Controller {

    private final ProductService productService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping()
    public ResponseEntity<?> filterProductsByNamePageable(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "subcategory", required = false) Long subCategoryId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sort", required = false) String sortBy,
            @RequestParam(name = "order", required = false, defaultValue = "asc") String sortOrder) {
        var filteredProduct = productService.filterAndSearchProductsByNamePageable(
                categoryId,
                subCategoryId,
                search,
                page,
                sortBy,
                sortOrder);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, filteredProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") final Long id){
        var product = productService.findProductById(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable(value = "id") final Long id,
                                               @RequestBody ProductRequest request){
        productService.updateProductById(id, request);
        var data = productService.findProductById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest request){
        var createdProductId = productService.saveProduct(request);
        var data = productService.findProductById(createdProductId);
        messagingTemplate.convertAndSend("/topic/products", data);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
