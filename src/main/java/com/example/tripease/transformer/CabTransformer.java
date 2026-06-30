package com.example.tripease.transformer;

import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Driver;

public class CabTransformer {

    public static Cab cabRequestToCab(CabRequest cabRequest)
    {
        return Cab.builder()
                .cabModel(cabRequest.getCabModel())
                .cabNumber(cabRequest.getCabNumber())
                .perKmRate(cabRequest.getPerKmRate())
                .available(true)
                .build();
    }
    public static CabResponse cabToCabResponse(Cab cab, Driver driver)
    {
        return CabResponse.builder()
                .cabModel(cab.getCabModel())
                .cabNumber(cab.getCabNumber())
                .perKmRate(cab.getPerKmRate())
                .available(cab.isAvailable())
                .driver(DriverTransformer.driverToDriverResponse(driver)) //which gets driver details of that cab
                .build();
    }

}
