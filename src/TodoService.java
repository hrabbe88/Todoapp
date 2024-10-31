package com.todoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service                    /*Annotation that this is a service class.*/
public class TodoService {

    private final TodoRepository todoRepository;
    /*Constructor for this class and a dependency by using Autowired annotation (for service and repository).*/
    @Autowired
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }
    /*A method to get all tasks in a list by repo in a return statement.*/
    public List<TodoList> getTasks() {
        return todoRepository.findAll();

    }
    /*Method to print out the TodoList in database.*/
    public void addNewTask(TodoList todoList) {
        System.out.println(todoList);
    }
    /*Method to delete task based by ID, with control of ID and exception. If ID does or does not exist
    * it will use "throw new" and give a message. If ID is found then the ID will be deleted. */
    public void deleteTaskById(Long id) {
        boolean exists = todoRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Uppgift ej hittad.");
        }
        todoRepository.existsById(id);
    }

}
