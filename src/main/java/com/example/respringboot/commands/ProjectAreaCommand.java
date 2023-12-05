package com.example.respringboot.commands;

import com.example.respringboot.model.AreaMasterDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProjectAreaCommand {
    private Long id;
    private String projectArea;
    private String description;
    private Set<AreaMasterDetailCommand> areaMasterDetailCommands = new HashSet<>();
}
