package com.alexsoft.entyties.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Notebook extends BaseEntity {

       //общая инфо
    private String dateComesToMarket;
    private String productLine;
    private String typeOfProduct;
    private String purpose;
//    @OneToMany(mappedBy = "modelId")
//    private Set<NotebookShop> shops;

    private String operatingSystem;

    //cpu
    private String platform;
    private String cpu;
    private String modelCpu;
    private String quantityCpu;
    private String frequencyOfCpu;
    private String turboFrequencyOfCpu;
    private String tdp;
    private String videoInternal;
    //корпус
    private String bodyMaterial;
    private String bodyColor;
    private String coverMaterial;
    private String coverColor;
    private String bodyLight;

    private String width;
    private String dept;
    private String thickness;
    private String weight;
    // монитор
    private String monitorSize;
    private String monitorResolution;
    private String frequencyOfMonitor;
    private String technologyOfMonitor;
    private String surfaceOfMonitor;
    private String matrixFrequency;
    private Boolean touchscreen;
    // ram
    private String typeOfRam;
    private String frequencyOfRam;
    private String volumeOfRam;
    private String maxSizeOfRam;
    private String numberOfSlots;
    private String freeSlotsOfMemory;

    //hdd
    private String hddConfiguration;
    private String typeOfHdd;
    private String volumeOfHdd;
    private String speedOfHdd;

    private String memoryCards;
    private String graficAdapter;

    private String camera;
    private String cameraPixels;
    private String mircofone;
    private String speakers;
    private String cursorManager;
    private String priteFunction;
    private String bluetooth;
    private String lan;
    private String wiFi;
    private String usbPorts;
    private String usb2;
    private String usb3;
    private String usb3_1;
    private String alternativeUsb;
    private String hdmi;
    private String audioJack;

    //Аккумулятор и время работы
    private String energy;
    private String timeOfWork;
    private String batteryCells;
    private String batteryCapacity;
    private String complect;

    //Графика
    private String videoExternal;
    private String ramOfVideoExternal;
}
