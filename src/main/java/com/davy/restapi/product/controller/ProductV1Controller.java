package com.davy.restapi.product.controller;

import com.davy.restapi.product.dto.ProductDetailsDTO;
import com.davy.restapi.product.dto.ProductRequestDTO;
import com.davy.restapi.product.response.ProductDetailResponse;
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
    public ResponseEntity<?> filterProducts(
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
        var response = new ProductDetailResponse();
        var product = productService.findById(id);
        response.setProduct((ProductDetailsDTO) product);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable(value = "id") final Long id,
                                               @RequestBody ProductRequestDTO request){
        var response = new ProductDetailResponse();
        productService.updateById(id, request);
        var product = productService.findById(id);
        response.setProduct((ProductDetailsDTO) product);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDTO request){
        var response = new ProductDetailResponse();
        var product = productService.save(request);
        response.setProduct((ProductDetailsDTO) product);
        messagingTemplate.convertAndSend("/topic/products", product);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }
}
