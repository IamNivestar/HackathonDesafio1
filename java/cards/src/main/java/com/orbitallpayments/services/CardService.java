package com.orbitallpayments.services;

import com.orbitallpayments.domain.Card;
import com.orbitallpayments.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public Card save(Card card){
        return cardRepository.save(card);
    }

    public List<Card> findAll(){
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll().forEach(cards::add);
        return cards;
    }

    public Optional<Card> findById(Long id){
        return  cardRepository.findById(id);
    }

    public void delete(Card card){
        cardRepository.delete(card);
    }
}