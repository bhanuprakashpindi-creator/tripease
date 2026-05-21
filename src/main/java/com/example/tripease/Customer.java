package com.example.tripease;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    private int customerId;
    private String name;
    private int age;
    private  String emailId;
    private Gender gender;
    @OneToMany(cascade = CascadeType.ALL)
            @JoinColumn(name = "customer_id")
    List<Booking> bookings= new ArrayList<>();
}
