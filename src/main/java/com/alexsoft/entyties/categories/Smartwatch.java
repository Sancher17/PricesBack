package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
public class Smartwatch extends BaseEntity {

    private String releaseDateOnMarket;
    private String ram;
    private String flashMemory;
    private String type;
    private String softwarePlatform;
    private String platformSupport;
    private String withSIMCard;
    private String caseMaterial;
    private String colorOfBody;
    private String braceletMaterial;
    private String colorOfBracelet;
    private String notice;
    private String calls;
    private String remoteControl;
    private String dustproofAndMoistureProof;
    private String length;
    private String width;
    private String thickness;
    private String weight;
    private String screenTechnology;
    private String screenSize;
    private String screenResolution;
    private String resolutionOfScreen;
    private String touchScreen;
    private String batteryType;
    private String batteryCapacity;
    private String whileWorking;
}
