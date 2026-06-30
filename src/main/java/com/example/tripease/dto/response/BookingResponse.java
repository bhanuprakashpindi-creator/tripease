package com.example.tripease.dto.response;

import com.example.tripease.Enum.TripStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    private Integer bookingId;
    private String pickup;
    private String destination;
    private double tripDistanceInKm;
    private TripStatus tripStatus;
    private double billAmount;
    private Date bookedAt;
   private  Date lastUpdateAt;
   private CabResponse cab;
   private CustomerResponse customer;
}
