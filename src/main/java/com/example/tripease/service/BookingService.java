package com.example.tripease.service;

import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.exception.CabNotAvailableException;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.BookingRepository;
import com.example.tripease.repository.CabRepository;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    CabRepository cabRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public BookingResponse addBooking(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty())
        {
            throw new CustomerNotFoundException("Customer With This Id Not Exists !!!");
        }
        Customer customer=optionalCustomer.get();
        Cab cab=cabRepository.getAvailableCabByRandom();
        if(cab==null)
        {
            throw new CabNotAvailableException("No Cabs Are Available Right Now!!!!");
        }
        Booking booking=BookingTransformer.bookingRequestToBooking(bookingRequest,cab.getPerKmRate());
        Booking savedBooking=bookingRepository.save(booking);

        cab.setAvailable(false);
        customer.getBookings().add(savedBooking);

        Driver driver=driverRepository.getDriverByCabId(cab.getCabId());
        driver.getBookings().add(savedBooking);

        Driver savedDriver=driverRepository.save(driver);
       Customer savedCustomer= customerRepository.save(customer);
        sendEmail(savedCustomer,booking,cab,savedDriver);
       return BookingTransformer.bookingToBookingResponse(savedBooking,cab,savedDriver,savedCustomer);
    }

    private void sendEmail(Customer customer,Booking booking,Cab cab,Driver driver)
    {

        String text =
                "Dear " + customer.getName() + ",\n\n" +

                        "🚖 Your cab booking has been confirmed successfully!\n\n" +

                        "━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "        BOOKING DETAILS\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

                        "📌 Booking ID      : " + booking.getBookingId() + "\n" +
                        "📍 Pickup Location : " + booking.getPickup() + "\n" +
                        "📍 Destination     : " + booking.getDestination() + "\n" +
                        "🛣 Distance         : " + booking.getTripDistanceInKm() + " KM\n" +
                        "💰 Total Fare      : ₹" + booking.getBillAmount() + "\n" +
                        "📅 Booking Time    : " + booking.getBookedAt() + "\n" +
                        "🚦 Trip Status     : " + booking.getTripStatus() + "\n\n" +

                        "━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "         CAB DETAILS\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

                        "🚘 Cab Number      : " + cab.getCabNumber() + "\n" +
                        "🚗 Cab Model       : " + cab.getCabModel() + "\n" +
                        "💵 Per KM Rate     : ₹" + cab.getPerKmRate() + "\n\n" +

                        "━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "       DRIVER DETAILS\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━\n\n" +

                        "👨 Driver Name      : " + driver.getName() + "\n" +
                        "📧 Driver Email     : " + driver.getEmailId() + "\n\n" +

                        "We hope you enjoy a safe and comfortable ride with TripEase. 🚀\n\n" +

                        "Thank you for choosing TripEase!\n\n" +

                        "Regards,\n" +
                        "Team TripEase";


        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("kirananna863@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab Booked!!!");
        simpleMailMessage.setText(text);
        simpleMailMessage.setSentDate(booking.getBookedAt());

        javaMailSender.send(simpleMailMessage);
    }

}
