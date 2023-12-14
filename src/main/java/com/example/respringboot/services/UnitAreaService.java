package com.example.respringboot.services;

import com.example.respringboot.commands.UnitAreaCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitArea;

import java.util.Set;

public interface UnitAreaService {
    Set<UnitAreaCommand> getUnitAreaCommands();

    UnitArea findById(Long l);

    void deleteById(Long idToDelete);

    UnitAreaCommand saveUnitAreaCommand(UnitAreaCommand command);
    UnitArea updateUnitArea(UnitArea unitArea, Long l);

    UnitAreaCommand findUnitAreaCommandById(Long l);
}
