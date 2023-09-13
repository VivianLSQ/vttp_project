package sg.nus.edu.iss.vttp_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.iss.vttp_project.models.Task;
import sg.nus.edu.iss.vttp_project.models.TaskDetails;
import sg.nus.edu.iss.vttp_project.services.TaskService;

@Controller
@RequestMapping(path="/api")
public class TaskManagementController {
    
    @Autowired
    private TaskService service;

    // @GetMapping(path="/")
    // public String mainPage(Task Task){
        
    // }

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("taskDetails", new TaskDetails());
        return "index";
    }

    @PostMapping(path = "/addTask", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String newTask(@ModelAttribute Task task, @ModelAttribute TaskDetails taskDetails) {

       if (service.newTaskService(task, taskDetails)) {
        return "success";
       } else {
        return "index";
       }
    }
    
}
