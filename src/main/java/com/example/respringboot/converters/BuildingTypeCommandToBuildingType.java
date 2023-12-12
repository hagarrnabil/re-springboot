package com.example.respringboot.converters;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.BuildingType;
import com.example.respringboot.model.Company;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingTypeCommandToBuildingType implements Converter<BuildingTypeCommand, BuildingType> {
    private final BuildingCommandToBuilding buildingConverter;

    public BuildingTypeCommandToBuildingType(BuildingCommandToBuilding buildingConverter) {
        this.buildingConverter = buildingConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BuildingType convert(BuildingTypeCommand source) {

        if (source == null) {
            return null;
        }

        final BuildingType buildingType = new BuildingType();
        buildingType.setBuildingTypeCode(source.getId());
        buildingType.setBuildingTypeId(source.getBuildingTypeId());
        buildingType.setBuildingTypeDescr(source.getBuildingTypeDescr());
        if (source.getBuildingCommands() != null && source.getBuildingCommands().size() > 0){
            source.getBuildingCommands()
                    .forEach( buildingCommand -> buildingType.getBuildings().add(buildingConverter.convert(buildingCommand)));
        }
        return buildingType;
    }
}
