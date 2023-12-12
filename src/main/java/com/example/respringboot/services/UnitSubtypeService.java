package com.example.respringboot.services;

import com.example.respringboot.commands.UnitSubtypeCommand;
import com.example.respringboot.model.UnitSubtype;

import java.util.Set;

public interface UnitSubtypeService {
    Set<UnitSubtypeCommand> getUnitSubtypeCommands();

    UnitSubtype findById(Long l);

    void deleteById(Long idToDelete);

    UnitSubtypeCommand saveUnitSubtypeCommand(UnitSubtypeCommand command);

    UnitSubtypeCommand findUnitSubtypeCommandById(Long l);
}
