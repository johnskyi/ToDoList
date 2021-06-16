package main.services;

import main.entityes.TaskEntity;
import main.repositoryes.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private  final TaskRepository taskRepository;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<TaskEntity> list()
    {
        Iterable<TaskEntity> taskIterable = taskRepository.findAll();
        ArrayList<TaskEntity> tasks = new ArrayList<>();
        for(TaskEntity task : taskIterable)
        {
            tasks.add(task);
        }
        return tasks;
    }
    public TaskEntity add(TaskEntity task)
    {
      return taskRepository.save(task);
    }
    public ResponseEntity deleteById(@PathVariable int id)
    {

        if(!taskRepository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
    public void deleteAll()
    {
        taskRepository.deleteAll();
    }

    public  ResponseEntity<TaskEntity> checkTask(@PathVariable int id)
    {

        Optional<TaskEntity> taskOptional = taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskOptional.get().setUnCheck(true);
        taskRepository.save(taskOptional.get());
        return ResponseEntity.ok(taskOptional.get());

    }

    public ResponseEntity get(@PathVariable int id)
    {

        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
        return new ResponseEntity(optionalTask.get(),HttpStatus.OK);
    }
}
