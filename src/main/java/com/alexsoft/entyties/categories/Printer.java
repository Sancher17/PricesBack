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
public class Printer extends BaseEntity {

    private String releaseDateOnMarket;
    private String type;
    private String format;
    private String print;
    private String printTechnology;
    private String numberOfColors;
    private String speedBwPrintA4;
    private String printSpeedColourA4;
    private String printing10х15см;
    private String tonerCartridge;
    private String operatingSystem;
    private String colorOfBody;
    private String maximumResolutionDpi;
    private String copyResolutionDpi;
    private String maximumNumberOfCopies;
    private String capacityInputTrays;
    private String maxCapacityInputTrays;
    private String capacityOfOutputTrays;
    private String usb;
    private String equipment;
    private String width;
    private String height;
    private String depth;
    private String weight;
}
