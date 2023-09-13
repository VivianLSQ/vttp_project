package sg.nus.edu.iss.vttp_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.edu.iss.vttp_project.models.Task;
import sg.nus.edu.iss.vttp_project.models.TaskDetails;
import sg.nus.edu.iss.vttp_project.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo; 

    @Transactional
    public Boolean newTaskService(Task task, TaskDetails details) {

        Boolean isTaskCreated = false;
        Integer TaskKey = taskRepo.addNewTask(task);

        if (TaskKey != null) {
            isTaskCreated = true;
        }

        details.setId(String.valueOf(TaskKey)); //note TaskKey is a integer 
        Boolean isTaskDetailsCreated = false;

        if (taskRepo.addTaskDetails(details)) {
            isTaskDetailsCreated = true;
        }

        if (isTaskCreated && isTaskDetailsCreated) {
            return true;
        } else {
            return false;
        }
    }
    
}
