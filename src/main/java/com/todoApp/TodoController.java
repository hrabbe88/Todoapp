package com.todoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/*Api controller and recognizer to take care of HTTP request (get, post,put,delete).*/
@RestController

/*Requestmapping for http CRUD management with path gettasks.*/
@RequestMapping(path = "/gettasks")
public class TodoController {

    /*A call on both repository and service layers*/
    private final TodoService todoService;
    private final TodoRepository todoRepository;


    /*Injection of dependencies for the constructor in class todocontroller.*/
    @Autowired
    public TodoController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }


    /*GetMapping annotation to get data from database into a list and return of tasks.*/
    @GetMapping
    public List<TodoList> getTasks() {
     return todoService.getTasks();
    }


    /*PostMapping annotation to add a new task with a path and a requestbody.*/
    @PostMapping(path = "/addnewtask")
    public void addNewTask (@RequestBody TodoList todoList){
        todoService.addNewTask(todoList);
    }


    /*PutMapping annotation and Requestbody annotation to update data IF ID exist.
    If ID exists the then the task,descrption, due date, priority and status will be updated*/
    @PutMapping(path = "{updateTaskById}")
    public void updateNewTask(@PathVariable("updateTaskById") Long id, @RequestBody TodoList updatedTask){
        Optional<TodoList> optionalTask = todoRepository.findById(id);

        /*If ID exist then it will be saved to the list.*/
        if(optionalTask.isPresent()) {
            TodoList existingTask = optionalTask.get();
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setTask(updatedTask.getTask());
        }
    }
    /*DeleteMapping annotering to erase data by request from client. (for ID)*/

    @DeleteMapping(path ="{deleteTaskById}")
    public void deleteTaskById (@PathVariable("deleteTaskById")Long id) {
        todoService.deleteTaskById(id);

    }
}


