package main.controllers;

import main.entityes.TaskEntity;
import main.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class TaskController {

    private final   TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    @GetMapping("/tasks/")
    public List<TaskEntity> getList()
    {
        return taskService.list();
    }

    @PostMapping(path = "/tasks/", consumes = "application/x-www-form-urlencoded")
    public int postAdd( TaskEntity task)
    {

        TaskEntity newTask = taskService.add(task);
        return newTask.getId();
    }

    @DeleteMapping("/tasks/{id}")
    public  ResponseEntity deleteById(@PathVariable int id)
    {
        return taskService.deleteById(id);
    }

    @DeleteMapping("/tasks/")
    public void deleteAll()
    {
        taskService.deleteAll();
    }

    @PutMapping("/tasks/{id}")
    public  void checkTask(@PathVariable int id)
    {
        taskService.checkTask(id);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        return taskService.get(id);
    }

}
