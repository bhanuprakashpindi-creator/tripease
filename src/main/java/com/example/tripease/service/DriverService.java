package com.example.tripease.service;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.exception.DriverNotFoundException;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    public DriverResponse addDriver(DriverRequest driverRequest) {

        // driverRequest -> entity
        Driver driver= DriverTransformer.driverRequestToDriver(driverRequest);

        //save entity in db

        Driver savedDriver=driverRepository.save(driver);

        //entity -> driverResponse

        return DriverTransformer.driverToDriverResponse(savedDriver);

    }

    public DriverResponse getDriver(int driverId) {
       Optional<Driver> optionalDriver =driverRepository.findById(driverId);
       if(optionalDriver.isEmpty())
       {
           throw  new DriverNotFoundException("Driver Not Found With This Id");
       }
       Driver driver=optionalDriver.get();
       DriverResponse driverResponse=DriverTransformer.driverToDriverResponse(driver);
       return driverResponse;
    }

    public List<DriverResponse> getDriverByAge(int age) {
        List<Driver> driverList=driverRepository.findByAge(age);
        //entity -> dto
        List<DriverResponse> driverResponses=new ArrayList<>();
        for(Driver driver:driverList)
        {
            driverResponses.add(DriverTransformer.driverToDriverResponse(driver));
        }
        return driverResponses;
    }

    public List<DriverResponse> getDriverByAgeGreaterThan(int age) {
        List<Driver> driverList=driverRepository.findDriverByAgeGreaterThan(age);
        List<DriverResponse> driverResponses=new ArrayList<>();
        for(Driver driver:driverList)
        {
            driverResponses.add(DriverTransformer.driverToDriverResponse(driver));
        }
        return driverResponses;
    }
}
