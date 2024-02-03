package com.davy.restapi.card.controller;

import com.davy.restapi.card.request.CardRequest;
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
        var data = cardService.findById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping
    public ResponseEntity<?> findAllCards(){
        var data = cardService.findAll();
        System.out.println("Data from cardService: " + data);
        var response = ResponseHandler.generateResponse(true, HttpStatus.OK, data);
        System.out.println("Response to client: " + response);
        return response;
    }

    @PostMapping
    public ResponseEntity<?> saveCard(@RequestBody CardRequest request){
        var data = cardService.save(request);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCardById(@PathVariable(value = "id") final Long id,
                                               @RequestBody CardRequest request){
        cardService.updateById(id, request);
        var data = cardService.findById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
