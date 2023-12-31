package com.example.respringboot.services;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.converters.UnitOrientationCommandToUnitOrientation;
import com.example.respringboot.converters.UnitOrientationToUnitOrientationCommand;
import com.example.respringboot.model.UnitOrientation;
import com.example.respringboot.repositories.UnitOrientationRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOrientationServiceImpl implements UnitOrientationService{
    private final UnitOrientationToUnitOrientationCommand unitOrientationToUnitOrientationCommand;
    private final UnitOrientationCommandToUnitOrientation unitOrientationCommandToUnitOrientation;
    private final UnitOrientationRepository unitOrientationRepository;

    public UnitOrientationServiceImpl(UnitOrientationToUnitOrientationCommand unitOrientationToUnitOrientationCommand,
                                      UnitOrientationCommandToUnitOrientation unitOrientationCommandToUnitOrientation,
                                      UnitOrientationRepository unitOrientationRepository)
    {
        this.unitOrientationToUnitOrientationCommand = unitOrientationToUnitOrientationCommand;
        this.unitOrientationCommandToUnitOrientation = unitOrientationCommandToUnitOrientation;
        this.unitOrientationRepository = unitOrientationRepository;
    }

    @Override
    @Transactional
    public Set<UnitOrientationCommand> getUnitOrientationCommands() {
        return StreamSupport.stream(unitOrientationRepository.findAll()
                        .spliterator(), false)
                .map(unitOrientationToUnitOrientationCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitOrientation findById(Long l) {
        Optional<UnitOrientation> unitOrientationOptional = unitOrientationRepository.findById(l);

        if (!unitOrientationOptional.isPresent()) {
            throw new RuntimeException("Unit Orientation Not Found!");
        }

        return unitOrientationOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitOrientationRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitOrientationCommand saveUnitOrientationCommand(UnitOrientationCommand command) {

        UnitOrientation detachedUnitOrientation = unitOrientationCommandToUnitOrientation.convert(command);
        UnitOrientation savedUnitOrientation = unitOrientationRepository.save(detachedUnitOrientation);
        log.debug("Saved Unit Orientation Id:" + savedUnitOrientation.getUnitOrientationCode());
        return unitOrientationToUnitOrientationCommand.convert(savedUnitOrientation);

    }

    @Override
    public UnitOrientation updateUnitOrientation(UnitOrientationCommand newUnitOrientationCommand, Long l) {
        return unitOrientationRepository.findById(l).map(oldUnitOrientation -> {
            if (newUnitOrientationCommand.getOrientationId() != oldUnitOrientation.getOrientationId()) oldUnitOrientation.setOrientationId(newUnitOrientationCommand.getOrientationId());
            if (newUnitOrientationCommand.getOrientationDescr() != oldUnitOrientation.getOrientationDescr()) oldUnitOrientation.setOrientationDescr(newUnitOrientationCommand.getOrientationDescr());
            return unitOrientationRepository.save(oldUnitOrientation);
        }).orElseThrow(() -> new RuntimeException("Unit Orientation not found"));
    }

    @Override
    @Transactional
    public UnitOrientationCommand findUnitOrientationCommandById(Long l) {
        return unitOrientationToUnitOrientationCommand.convert(findById(l));
    }
}
