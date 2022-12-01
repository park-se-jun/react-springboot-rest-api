package com.example.movie_reservation.user;

import org.springframework.lang.NonNull;

import java.util.UUID;

//엔티티
public class User {
    @NonNull
    final UUID id;
    @NonNull
    final String  phone;

    public User(@NonNull UUID id, @NonNull String phone) {
        this.id = id;
        this.phone = phone;
    }
}
