package com.example.tripease.controller;


import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.model.Driver;
import com.example.tripease.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService driverService;
    @PostMapping("/add")
    public DriverResponse addDriver( @RequestBody DriverRequest driverRequest)
    {
        return driverService.addDriver(driverRequest);
    }
    @GetMapping("/get/driver-id/{id}")
    public DriverResponse getDriver(@PathVariable("id") int driverId)
    {
        return driverService.getDriver(driverId);
    }
    @GetMapping("/get-by-age/{age}")
    public List<DriverResponse> getDriverByAge(@PathVariable("age") int age)
    {
        return driverService.getDriverByAge(age);
    }
    @GetMapping("/get-by-age-greater-than")
    public List<DriverResponse> getDriverByAgeGreaterThan(@RequestParam("age") int age)
    {
        return driverService.getDriverByAgeGreaterThan(age);
    }


}
