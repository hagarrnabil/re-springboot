package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Location;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.model.Project;
import com.example.respringboot.repositories.ProjectRepository;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectCommandToProject implements Converter<ProjectCommand, Project> {
    private final BuildingCommandToBuilding buildingConverter;
    private final ProjectRepository projectRepository;

    public ProjectCommandToProject(BuildingCommandToBuilding buildingConverter, ProjectRepository projectRepository) {
        this.buildingConverter = buildingConverter;
        this.projectRepository = projectRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Project convert(ProjectCommand source) {

        if (source == null) {
            return null;
        }

        final Project project = new Project();
        project.setProjectCode(source.getId());
        if (source.getCompanyCode() != null) {
            Company company = new Company();
            company.setCompanyCode(source.getCompanyCode());
            project.setCompany(company);
            company.addProject(project);
        }
        if (source.getProfitCode() != null) {
            ProfitCenter profitCenter = new ProfitCenter();
            profitCenter.setProfitCode(source.getProfitCode());
            project.setProfitCenter(profitCenter);
            profitCenter.addProject(project);
        }
        if (source.getLocationCode() != null) {
            Location location = new Location();
            location.setLocationCode(source.getLocationCode());
            project.setLocation(location);
            location.setProject(project);
        }
        project.setProjectId(source.getProjectId());
        project.setProjectDescription(source.getProjectDescription());
        project.setValidFrom(source.getValidFrom());
        project.setProfit(source.getProfit());
        if (source.getBuildingCommands() != null && source.getBuildingCommands().size() > 0) {
            source.getBuildingCommands()
                    .forEach(buildingCommand -> project.getBuildings().add(buildingConverter.convert(buildingCommand)));
        }
        return project;

    }

}
