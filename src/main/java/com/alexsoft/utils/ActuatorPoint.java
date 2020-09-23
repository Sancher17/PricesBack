package com.alexsoft.utils;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id="mypoint")
@Component
public class ActuatorPoint {

    @ReadOperation
    public Integer mypoint(){
        int counter = 0;
        return  counter++;
    }
}
