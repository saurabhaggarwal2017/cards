package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.dto.CardDetailsUpdateDto;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardMapper;
import com.eazybytes.cards.repository.CardRepository;
import com.eazybytes.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardService implements ICardService {
    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber, String cardOwnerName) {
        // on one mobile number can have multiple cards.
        Card card = new Card();
        card.setCardOwnerName(cardOwnerName);
        card.setMobileNumber(mobileNumber);
        card.setCardType("VISA");
        Card savedCard = cardRepository.save(createCardForUser(card));
    }

    @Override
    public List<CardDto> getAllCardDetails(String mobileNumber) {
        List<Card> allCards = cardRepository.findAllByMobileNumber(mobileNumber);
        if (allCards.isEmpty()) {
            throw new ResourceNotFoundException("Card", "mobileNumber", mobileNumber);
        }
        return allCards.stream()
                .map(card -> CardMapper.mapToCardDto(card, new CardDto()))
                .collect(Collectors.toList());
    }

    @Override
    public CardDto getCardDetails(String mobileNumber, long cardNumber) {
        Card card = cardRepository.findByMobileNumberAndCardNumber(mobileNumber, cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber and CardNumber", String.format("%s, %s", mobileNumber, String.valueOf(cardNumber))));
        return CardMapper.mapToCardDto(card, new CardDto());
    }

    @Override
    public void updateCardDetails(String mobileNumber, long cardNumber, CardDetailsUpdateDto updateDto) {
        Card card = cardRepository.findByMobileNumberAndCardNumber(mobileNumber, cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber and CardNumber", String.format("%s, %s", mobileNumber, String.valueOf(cardNumber))));
        card.setCardOwnerName(updateDto.getCardOwnerName());
        card.setCardLimit(updateDto.getCardLimit());
        card.setAvilableBalance(updateDto.getCardLimit());
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(String mobileNumber, long cardNumber) {
        Card card = cardRepository.findByMobileNumberAndCardNumber(mobileNumber, cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber and CardNumber", String.format("%s, %s", mobileNumber, String.valueOf(cardNumber))));
        cardRepository.delete(card);
    }

    private Card createCardForUser(Card card) {
        //This means the cardNumber can range from 1,000,000,000,000,000 to 9,999,999,999,999,999.
        long cardNumber = 1000000000000000L * (new Random().nextInt(9) + 1) + new Random().nextLong(1000000000000000L);
        card.setCardNumber(cardNumber);
        int month = new Random().nextInt(12) + 1;
        int year = new Random().nextInt(3) + 3 + 2025; // year of expiry generate in between 3 yr to 5 yr.
        card.setValidDate(String.format("%s / %s", month, year));
        int cvv = 100 + new Random().nextInt(100); // 100 to 199
        card.setCvv(cvv);
        int limit = (new Random().nextInt(10) + 1) * 10000; // 10,000 to 1,00,000
        card.setCardLimit(limit);
        card.setAvilableBalance(limit);
        card.setAmountUsed(0);
        return card;
    }
}
