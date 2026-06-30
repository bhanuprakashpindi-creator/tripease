package com.example.tripease.transformer;

import com.example.tripease.Enum.TripStatus;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;

public class BookingTransformer {
    public static Booking bookingRequestToBooking(BookingRequest bookingRequest,double perKmRate)
    {
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.INPROGRESS)
                .billAmount(bookingRequest.getTripDistanceInKm() * (perKmRate))
                .build();
    }
    public static BookingResponse bookingToBookingResponse(Booking booking, Cab cab , Driver driver, Customer customer)
    {
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .cab(CabTransformer.cabToCabResponse(cab,driver))
                .customer(CustomerTransformer.customerToCustomerResponse(customer))
                .build();
    }
}
