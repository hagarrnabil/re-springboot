package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "profit_center")
public class ProfitCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String profitId;
    @NotNull
    private String profitDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profitCenter")
    private Set<Project> projects = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profitCenter")
    private Set<Building> buildings = new HashSet<>();

}