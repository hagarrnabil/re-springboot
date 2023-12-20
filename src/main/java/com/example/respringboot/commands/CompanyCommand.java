package com.example.respringboot.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CompanyCommand implements Serializable {

    private Long id;
    private String companyCodeId;
    private String companyCodeDescription;
    @JsonIgnore
    private Set<ProjectCommand> projectCommands = new HashSet<>();

}
