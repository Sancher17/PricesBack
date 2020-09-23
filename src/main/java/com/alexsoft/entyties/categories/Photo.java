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
public class Photo extends BaseEntity {

    private String releaseDateOnMarket;
    private String cameraType;
    private String kit;
    private String typeOfMatrix;
    private String screenSize;
    private String numberOfPointsOfMatrix;
    private String physicalSizeOfMatrix;
    private String maximumVideoResolution;
    private String shootingModesHdVideo;
    private String caseMaterial;
    private String colorOfBody;
    private String length;
    private String width;
    private String thickness;
    private String weight;
    private String screenType;
    private String numberOfPixelsOfScreen;
    private String viewfinder;
    private String minimumSensitivityIso;
    private String maximumSensitivityIso;
    private String extendedLightSensitivityIso;
    private String abilityToChangeLens;
    private String maximumImageResolution;
    private String numberOfCapturePointsAutoFocus;
    private String maximumNumberOfFramesPerSecond;
    private String exposureCompensation;
    private String whiteBalance;
    private String maximumExposure;
    private String minimumShutterSpeed;
    private String flashSyncSpeed;
    private String speedShooting;
    private String bufferSize;
    private String memoryCardSupport;
    private String gps;
    private String bluetooth;
    private String wifi;
    private String supportRemoteControl;
    private String batteryType;
    private String batteryCapacity;
    private String batteryLife;
    private String equipment;
}
