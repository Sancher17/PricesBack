package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tablet extends BaseEntity {

    private String marketEntryDate;
    private String purpose;
    private String screenDiagonal;
    private String screenResolution;
    private String screenMatrix;
    private String operatingSystem;
    private String cpu;
    private String graphicsAccelerator;
    private String ram;
    private String innerMemory;
    private String numberOfCores;
    private String clockFrequency;
    private String bodyMaterial;
    private String bodyColor;
    private String length;
    private String width;
    private String thickness;
    private String weight;
    private String camera;
    private String numberOfCameraPixels;
    private String numberOfActivePixelsOfTheFrontCamera;
    private String builtInMicrophone;
    private String builtInSpeakers;
    private String bluetooth;
    private String wifi;
    private String audioOutputs;
    private String energyReserve;
    private String workingHours;
    private String contentsOfDelivery;
}
