package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Smartphone extends BaseEntity {

    //общая инфо
    private String dateComesToMarket;
    private String typeOfProduct;
    private String operationSystem;
    private String operationSystemVersino;

    // экран
    private String screenSize;
    private String screenResolution;

    // ram
    private String ram;
    private String fleshRam;

    //камера
    private String camera;
    private String cameraQuantity;
    private String cameraMPX;


    private String simQuantity;
    private String simFormat;

    private String maxVideoResolution;
    private String fleshCards;

    //cpu
    private String platform;
    private String cpu;
    private String quantityCpu;
    private String frequencyOfCpu;
    private String modelCpu;
    private String bitCpu;
    private String techProcess;

    //Графика
    private String grafic;

    //корпус
    private String bodyConstraction;
    private String bodyMaterial;
    private String bodyColor;
    private String frontPanelColor;

    private String frontCameraLocation;
    private String scannerLocation;

    private String length;
    private String width;
    private String thickness;
    private String weight;

    private String screenTechnology;
    private String quantityOfColors;
    private String screenDpi;
    private String aspectRatio;
    private String cameraCharacteristic;
    private String additionalModulesCamera;
    private String apertureMainCamera;
    private String maxCadrsPerSec;
    private String frontCamera;
    private String aperture;
    private String supportedFrequencies;
    private String bluetooth;
    private String audioOut;
    private String wifi;
    private String connectingPlug;
    private String accumType;
    private String accumCapacity;
    private String talkTime;
    private String videoTime;
    private String audioTime;
}
