package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.ProjectDto;
import com.enterprisepro.erp.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    Page<ProjectDto> getAllProjects(String search, Pageable pageable);
    ProjectDto getProjectById(Long id);
    ProjectDto createProject(ProjectDto projectDto);
    ProjectDto updateProject(Long id, ProjectDto projectDto);
    void deleteProject(Long id);

    // Tasks
    List<TaskDto> getProjectTasks(Long projectId);
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTaskStatus(Long taskId, String status);
}
