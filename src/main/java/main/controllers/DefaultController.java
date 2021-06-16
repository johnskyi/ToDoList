package main.controllers;

import main.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    private final TaskService taskService;

    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("tasksCount",taskService.list().size());
        model.addAttribute("tasks",taskService.list());



        return "index";
    }
}
