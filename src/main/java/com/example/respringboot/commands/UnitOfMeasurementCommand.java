package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasurementCommand {
    private Long id;
    private String uomID;
    private String uomDescr;
//    private AreaMasterDetailCommand areaMasterDetailCommand;
}
