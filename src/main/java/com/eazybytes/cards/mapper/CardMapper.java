package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;


public class CardMapper {
//    public static Logger logger = LoggerFactory.getLogger(CardMapper.class);
    public static CardDto mapToCardDto(Card card, CardDto cardDto) {
        cardDto.setCardOwnerName(card.getCardOwnerName());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setValidDate(card.getValidDate());
//        logger.info("-------------- cvv ------------ : {}",card.getCvv());
        cardDto.setCvv(card.getCvv());
        cardDto.setCardLimit(card.getCardLimit());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvilableBalance(card.getAvilableBalance());
        return cardDto;
    }
}
