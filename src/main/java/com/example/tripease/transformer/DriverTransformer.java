package com.example.tripease.transformer;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.model.Driver;

public class DriverTransformer {
    public static Driver driverRequestToDriver(DriverRequest driveRequest)
    {
        return Driver.builder()
                .name(driveRequest.getName())
                .age(driveRequest.getAge())
                .emailId(driveRequest.getEmailId())
                .build();
    }
    public static DriverResponse driverToDriverResponse(Driver driver)
    {
        return DriverResponse.builder()
                .driverId(driver.getDriverId())
                .name(driver.getName())
                .age(driver.getAge())
                .emailId(driver.getEmailId())
                .build();
    }


}
