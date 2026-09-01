package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.ProjectDto;
import com.enterprisepro.erp.dto.SubtaskDto;
import com.enterprisepro.erp.dto.TaskDto;
import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.entity.Employee;
import com.enterprisepro.erp.entity.Project;
import com.enterprisepro.erp.entity.Task;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.CustomerRepository;
import com.enterprisepro.erp.repository.EmployeeRepository;
import com.enterprisepro.erp.repository.ProjectRepository;
import com.enterprisepro.erp.repository.TaskRepository;
import com.enterprisepro.erp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> getAllProjects(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return projectRepository.findByNameContainingIgnoreCaseOrProjectCodeContainingIgnoreCase(search, search, pageable)
                    .map(this::mapProjectToDto);
        }
        return projectRepository.findAll(pageable).map(this::mapProjectToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        return mapProjectToDto(project);
    }

    @Override
    @Transactional
    public ProjectDto createProject(ProjectDto dto) {
        Project p = new Project();
        p.setProjectCode(StringUtils.hasText(dto.getProjectCode()) ? dto.getProjectCode() : "PRJ-" + (1000 + projectRepository.count() + 1));
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : LocalDate.now());
        p.setEndDate(dto.getEndDate());
        p.setBudget(dto.getBudget());
        p.setStatus(dto.getStatus() != null ? dto.getStatus() : "IN_PROGRESS");
        p.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");

        if (dto.getClientId() != null) {
            Customer client = customerRepository.findById(dto.getClientId()).orElse(null);
            p.setClient(client);
        }

        if (dto.getProjectManagerId() != null) {
            Employee pm = employeeRepository.findById(dto.getProjectManagerId()).orElse(null);
            p.setProjectManager(pm);
        }

        Project saved = projectRepository.save(p);
        return mapProjectToDto(saved);
    }

    @Override
    @Transactional
    public ProjectDto updateProject(Long id, ProjectDto dto) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setStartDate(dto.getStartDate());
        p.setEndDate(dto.getEndDate());
        p.setBudget(dto.getBudget());
        p.setStatus(dto.getStatus());
        p.setPriority(dto.getPriority());
        p.setProgressPercentage(dto.getProgressPercentage());

        if (dto.getClientId() != null) {
            Customer client = customerRepository.findById(dto.getClientId()).orElse(null);
            p.setClient(client);
        }

        if (dto.getProjectManagerId() != null) {
            Employee pm = employeeRepository.findById(dto.getProjectManagerId()).orElse(null);
            p.setProjectManager(pm);
        }

        Project updated = projectRepository.save(p);
        return mapProjectToDto(updated);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        projectRepository.delete(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getProjectTasks(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));

        return taskRepository.findByProject(project).stream()
                .map(this::mapTaskToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDto createTask(TaskDto dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", dto.getProjectId()));

        Task task = new Task();
        task.setProject(project);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : "TODO");
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");
        task.setDueDate(dto.getDueDate());
        task.setEstimatedHours(dto.getEstimatedHours());

        if (dto.getAssignedToId() != null) {
            Employee emp = employeeRepository.findById(dto.getAssignedToId()).orElse(null);
            task.setAssignedTo(emp);
        }

        Task saved = taskRepository.save(task);
        return mapTaskToDto(saved);
    }

    @Override
    @Transactional
    public TaskDto updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));
        task.setStatus(status.toUpperCase());
        return mapTaskToDto(taskRepository.save(task));
    }

    private ProjectDto mapProjectToDto(Project p) {
        ProjectDto dto = new ProjectDto();
        dto.setId(p.getId());
        dto.setProjectCode(p.getProjectCode());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        if (p.getClient() != null) {
            dto.setClientId(p.getClient().getId());
            dto.setClientName(p.getClient().getName());
        }
        if (p.getProjectManager() != null) {
            dto.setProjectManagerId(p.getProjectManager().getId());
            dto.setProjectManagerName(p.getProjectManager().getFullName());
        }
        dto.setStartDate(p.getStartDate());
        dto.setEndDate(p.getEndDate());
        dto.setBudget(p.getBudget());
        dto.setActualCost(p.getActualCost());
        dto.setProgressPercentage(p.getProgressPercentage());
        dto.setStatus(p.getStatus());
        dto.setPriority(p.getPriority());

        List<Task> tasks = taskRepository.findByProject(p);
        dto.setTotalTasks(tasks.size());
        dto.setCompletedTasks((int) tasks.stream().filter(t -> "COMPLETED".equals(t.getStatus())).count());
        return dto;
    }

    private TaskDto mapTaskToDto(Task t) {
        TaskDto dto = new TaskDto();
        dto.setId(t.getId());
        dto.setProjectId(t.getProject().getId());
        dto.setProjectName(t.getProject().getName());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setStatus(t.getStatus());
        dto.setPriority(t.getPriority());
        dto.setDueDate(t.getDueDate());
        dto.setEstimatedHours(t.getEstimatedHours());
        dto.setLoggedHours(t.getLoggedHours());
        if (t.getAssignedTo() != null) {
            dto.setAssignedToId(t.getAssignedTo().getId());
            dto.setAssignedToName(t.getAssignedTo().getFullName());
        }
        if (t.getSubtasks() != null) {
            dto.setSubtasks(t.getSubtasks().stream()
                    .map(st -> new SubtaskDto(st.getId(), st.getTitle(), st.isCompleted()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
