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
public class Tv extends BaseEntity {

    private String marketEntryDate;
    private String type;
    private String screenDiagonal;
    private String resolution;
    private String matrixFrequency;
    private String smartTv;
    private String caseColor;
    private String colorFrames;
    private String colorStands;
    private String stand;
    private String typeMatrix;
    private String depthOfColor;
    private String cpu;
    private String tvTuner;
    private String powerSoundSystem;
    private String audioCodecSupport;
    private String bluetooth;
    private String wifi;
    private String compositeIn;
    private String hdmi;
    private String versionHdmi;
    private String digitalOutputS_Pdif;
    private String usb;
    private String mountVesa;
    private String width;
    private String height;
    private String depth;
    private String heightWithoutStand;
    private String thicknessPanel;
    private String weightWithoutStands;
}
