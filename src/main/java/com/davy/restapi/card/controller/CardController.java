package com.davy.restapi.card.controller;

import com.davy.restapi.card.dto.CardDetailDTO;
import com.davy.restapi.card.dto.CardDTO;
import com.davy.restapi.card.dto.CardRequestDTO;
import com.davy.restapi.card.response.CardListResponse;
import com.davy.restapi.card.response.CardResponse;
import com.davy.restapi.card.service.CardService;
import com.davy.restapi.shared.handler.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id){
        var response = new CardResponse();
        var card = cardService.findById(id);
        response.setCard((CardDetailDTO) card);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<?> findAllCards(){
        var response = new CardListResponse();
        var cards = cardService.findAll();
        response.setCards((List<CardDTO>) cards);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }

    @PostMapping
    public ResponseEntity<?> saveCard(@RequestBody CardRequestDTO request){
        var response = new CardResponse();
        var card = cardService.save(request);
        response.setCard((CardDetailDTO) card);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCardById(@PathVariable(value = "id") final Long id,
                                            @RequestBody CardRequestDTO request){
        var response = new CardResponse();
        cardService.updateById(id, request);
        var card = cardService.findById(id);
        response.setCard((CardDetailDTO) card);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, response);
    }
}
