package com.davy.restapi.card.controller;

import com.davy.restapi.address.request.AddressCreateRequest;
import com.davy.restapi.address.request.AddressUpdateRequest;
import com.davy.restapi.card.request.CardCreateRequest;
import com.davy.restapi.card.request.CardUpdateRequest;
import com.davy.restapi.card.service.CardService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id){
        var data = cardService.findCardById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllCards(){
        var data = cardService.findAllCards();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping
    public ResponseEntity<?> saveCard(@RequestBody CardCreateRequest request){
        var data = cardService.saveCard(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCardById(@PathVariable(value = "id") final Long id,
                                               @RequestBody CardUpdateRequest request){
        var data = cardService.updateCardById(id, request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
