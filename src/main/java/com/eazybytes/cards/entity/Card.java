package com.eazybytes.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String cardOwnerName;
    private String mobileNumber;
    private long cardNumber;
    private String cardType;
    private String validDate;
    private int cvv;
    private int cardLimit;
    private int amountUsed;
    private int avilableBalance;

}
