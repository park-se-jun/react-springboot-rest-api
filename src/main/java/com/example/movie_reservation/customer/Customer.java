package com.example.movie_reservation.customer;

import org.springframework.lang.NonNull;

import java.util.UUID;

//엔티티
public class Customer {
    @NonNull
    final UUID id;
    @NonNull
    final String  phone;

    public Customer(@NonNull UUID id, @NonNull String phone) {
        this.id = id;
        this.phone = phone;
    }
}
