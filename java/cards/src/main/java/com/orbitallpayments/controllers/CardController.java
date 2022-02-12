package com.orbitallpayments.controllers;

import com.orbitallpayments.domain.Card;
import com.orbitallpayments.repositories.CardRepository;
import com.orbitallpayments.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card saveCard = cardService.save(card);
        System.out.print(saveCard);
        return new ResponseEntity(saveCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll(){
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        Optional<Card> fetchedCard = cardService.findById(id);

        return ResponseEntity.ok(fetchedCard.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> delete(@PathVariable Long id){
        Optional<Card> fetchedCard = cardService.findById(id);

        if(!fetchedCard.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fetchedCard.get());
        }
        cardService.delete(fetchedCard.get());
        return ResponseEntity.status(HttpStatus.OK).body(fetchedCard.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card card){
        Optional<Card> fetchedCard = cardService.findById(id);

        if(!fetchedCard.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fetchedCard.get());
        }
        var new_card = fetchedCard.get();
        new_card.setCardNumber(card.getCardNumber());
        new_card.setEmbossName(card.getEmbossName());
        new_card.setCustomerName(card.getCustomerName());
        new_card.setDocumentNumber(card.getDocumentNumber());
        new_card.setMotherName(card.getMotherName());
        new_card.setAddress(card.getAddress());
        new_card.setCity(card.getCity());
        Card saveCard = cardService.save(new_card);
        return ResponseEntity.status(HttpStatus.OK).body(saveCard);
    }

}

