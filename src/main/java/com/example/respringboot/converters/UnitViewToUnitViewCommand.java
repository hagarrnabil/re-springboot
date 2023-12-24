package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.model.UnitView;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitViewToUnitViewCommand implements Converter<UnitView, UnitViewCommand> {
    private final UnitToUnitCommand unitConverter;

    public UnitViewToUnitViewCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitViewCommand convert(UnitView source) {

        if (source == null) {
            return null;
        }

        final UnitViewCommand unitViewCommand = new UnitViewCommand();
        unitViewCommand.setId(source.getUnitViewCode());
        unitViewCommand.setuViewId(source.getuViewId());
        unitViewCommand.setuViewDescr(source.getuViewDescr());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> unitViewCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return unitViewCommand;
    }
}
