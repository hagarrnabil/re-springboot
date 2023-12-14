package com.example.respringboot.services;


import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitView;

import java.util.Set;

public interface UnitViewService {
    Set<UnitViewCommand> getUnitViewCommands();

    UnitView findById(Long l);

    void deleteById(Long idToDelete);

    UnitViewCommand saveUnitViewCommand(UnitViewCommand command);

    UnitView updateUnitView(UnitView unitView, Long l);

    UnitViewCommand findUnitViewCommandById(Long l);
}
