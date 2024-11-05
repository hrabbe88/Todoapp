package com.todoApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class TodoConfig {

    /*Injection of repository*/
    private final TodoRepository todoRepository;

    /*Constructor to inject repo dependency*/
    public TodoConfig(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /*The app will automatically save a task to the database by this setup.*/
    @ResponseBody

    /*Bean method to get defined by the Configuration annotation */
    /* CommandLineRunner saves data to database by default*/
    @Bean
    CommandLineRunner commandLineRunner(TodoRepository todoRepository){
        return args -> {
            TodoList TodoList = new TodoList(
                    "HR-Möte",
                    "Möte med HR ang Adam Hagberg",
                    LocalDate.of(2024, 12, 12),
                    com.todoApp.TodoList.Priority.HIGH,
                    com.todoApp.TodoList.Status.ACTIVE,
                    LocalDate.of(2024, 11, 1)
            );

            /*Injection to save all data above to the database.*/
            todoRepository.saveAll(
                    List.of(TodoList)
            );

        };

    }
    /*Scheduled call when I start the app will control if there are any tasks due to date. If so
    * then the app will tell the user that there is one day left for the task.*/
    @Scheduled
    public void checkForDueDates() {
        List<TodoList> tasks = todoRepository.findAll();
        for(TodoList task : tasks) {
            if(task.isDueTomorrow()) {
                System.out.println("Din uppgift" + task.getTask() + "går ut imorgon!");
            }
        }
    }
}
