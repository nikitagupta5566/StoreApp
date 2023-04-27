package com.kosuri.stores.handler;


import com.kosuri.stores.dao.TaskEntity;
import com.kosuri.stores.dao.TaskRepository;
import com.kosuri.stores.dao.TaskRoleEntity;
import com.kosuri.stores.dao.TaskRoleRepository;
import com.kosuri.stores.exception.APIException;
import com.kosuri.stores.model.request.GetTasksForRoleRequest;
import com.kosuri.stores.model.request.MapTaskForRoleRequest;
import com.kosuri.stores.model.response.GetAllTasksResponse;
import com.kosuri.stores.model.response.GetTasksForRoleResponse;
import com.kosuri.stores.model.role.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TaskHandler {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskRoleRepository taskRoleRepository;


    public GetAllTasksResponse getAllTasks(){
        GetAllTasksResponse response = new GetAllTasksResponse();
        List<TaskEntity> taskList = new ArrayList<TaskEntity>();
        taskRepository.findAll().forEach(task -> taskList.add(task));
        response.setTaskList(taskList);
        return response;
    }

    public void mapTaskRoleEntityFromRequest(MapTaskForRoleRequest request) throws Exception {

        for(Integer taskId: request.getTaskIds()) {
            TaskRoleEntity tempTaskRole = new TaskRoleEntity();
            tempTaskRole.setTaskId(taskId);
            Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskId);

            if (taskEntityOptional.isEmpty()) {
                throw new APIException(String.format("Task with id %s not found", taskId));
            }

            tempTaskRole.setTaskName(taskEntityOptional.get().getTaskName());
            tempTaskRole.setRoleId(request.getRoleId());
            tempTaskRole.setUpdatedBy(request.getUpdatedBy());
            LocalDate localDate = LocalDate.now();
            tempTaskRole.setUpdatedDate(localDate.toString());
            try {
                taskRoleRepository.save(tempTaskRole);
            } catch(Exception e){
                System.out.println(e.getCause());
            }
        }

    }


    public GetTasksForRoleResponse fetchAllTaskOfRole(GetTasksForRoleRequest request) {
        List<TaskRoleEntity> savedTaskForRole = taskRoleRepository.findByRoleId(request.getRoleId());
        List<Task> taskList = new ArrayList<>();

        for(TaskRoleEntity taskRoleEntity: savedTaskForRole) {
            Task task = new Task();
            task.setTaskId(taskRoleEntity.getTaskId());
            task.setTaskName(taskRoleEntity.getTaskName());
            taskList.add(task);
        }
        GetTasksForRoleResponse getTasksForRoleResponse = new GetTasksForRoleResponse();
        getTasksForRoleResponse.setTaskList(taskList);
        return getTasksForRoleResponse;

    }



}

