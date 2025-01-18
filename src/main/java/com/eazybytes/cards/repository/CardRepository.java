package com.eazybytes.cards.repository;

import com.eazybytes.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByMobileNumber(String mobileNumber);
    Optional<Card> findByMobileNumberAndCardNumber(String mobileNumber, long cardNumber);
}
