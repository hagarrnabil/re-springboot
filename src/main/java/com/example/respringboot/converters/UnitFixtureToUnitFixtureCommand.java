package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitFixture;
import com.example.respringboot.model.UsageType;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitFixtureToUnitFixtureCommand implements Converter<UnitFixture, UnitFixtureCommand> {
    private final UnitToUnitCommand unitConverter;

    public UnitFixtureToUnitFixtureCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitFixtureCommand convert(UnitFixture source) {

        if (source == null) {
            return null;
        }

        final UnitFixtureCommand unitFixtureCommand = new UnitFixtureCommand();
        unitFixtureCommand.setUnitFixtureCode(source.getUnitFixtureCode());
        unitFixtureCommand.setFixtureId(source.getFixtureId());
        unitFixtureCommand.setFixtureDescr(source.getFixtureDescr());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> unitFixtureCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return unitFixtureCommand;
    }
}
