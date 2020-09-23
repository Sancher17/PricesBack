package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler"
})
public class Console extends BaseEntity {

    private String releaseDateOnMarket;
    private String type;
    private String edition;
    private String amountOfDrive;
    private String maxResolutionInGames;
    private String opticalDrive;
    private String color;
    private String streamingGamesFromConsole;
    private String cpu;
    private String numberOfCores;
    private String graphicsProcessor;
    private String memory;
    private String memoryType;
    private String typeOfDrive;
    private String gamepad;
    private String supportBluetooth;
    private String powerController;
    private String batteryCapacity;
    private String numberOfGamepads;
    private String complete;
    private String wifi;
    private String ethernet;
    private String bluetooth;
    private String hdmiOutput;
    private String usb;
    private String length;
    private String width;
    private String height;
    private String weight;

}
