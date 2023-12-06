package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "building_area")
public class BuildingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String buildingArea;
    @NotNull
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingArea")
    private Set<AreaMasterDetail> areaMasterDetails = new HashSet<>();
}