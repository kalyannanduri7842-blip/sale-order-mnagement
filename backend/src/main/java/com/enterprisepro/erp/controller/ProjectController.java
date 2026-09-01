package com.enterprisepro.erp.controller;

import com.enterprisepro.erp.dto.ProjectDto;
import com.enterprisepro.erp.dto.TaskDto;
import com.enterprisepro.erp.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project & Task Management", description = "Endpoints for managing client projects, tasks, kanban workflow and resource assignments")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(summary = "Get paginated project list")
    public ResponseEntity<Page<ProjectDto>> getAllProjects(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(projectService.getAllProjects(search, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project details by ID")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROJECT_MANAGER')")
    @Operation(summary = "Create a new project")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto created = projectService.createProject(projectDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROJECT_MANAGER')")
    @Operation(summary = "Update an existing project")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.updateProject(id, projectDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'PROJECT_MANAGER')")
    @Operation(summary = "Delete a project")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // Tasks
    @GetMapping("/{projectId}/tasks")
    @Operation(summary = "Get tasks belonging to a project")
    public ResponseEntity<List<TaskDto>> getProjectTasks(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectTasks(projectId));
    }

    @PostMapping("/tasks")
    @Operation(summary = "Create a new task")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto created = projectService.createTask(taskDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{taskId}/status")
    @Operation(summary = "Update task status (TODO, IN_PROGRESS, REVIEW, COMPLETED)")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long taskId, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        return ResponseEntity.ok(projectService.updateTaskStatus(taskId, status));
    }
}
