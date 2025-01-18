package com.eazybytes.cards.service;


import com.eazybytes.cards.dto.CardDetailsUpdateDto;
import com.eazybytes.cards.dto.CardDto;

import java.util.List;

public interface ICardService {
    void createCard(String mobileNumber, String cardOwnerName);

    List<CardDto> getAllCardDetails(String mobileNumber);

    CardDto getCardDetails(String mobileNumber, long cardNumber);

    void updateCardDetails(String mobileNumber, long cardNumber, CardDetailsUpdateDto updateDto);

    void deleteCard(String mobileNumber, long cardNumber);


}
