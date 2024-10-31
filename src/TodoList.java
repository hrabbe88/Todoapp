package com.todoApp;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.time.LocalDate;



    @Entity(name = "todoList")    /* Entity annotation to map current class to database.*/
    @Table                      /*Table annotation to tell the database hat this will be mapped as a table.*/


    public class TodoList {       /* primitive types for my list (TodoList) that will be stored to the database.*/



    @Id                                 /* Id will be unique*/
    @SequenceGenerator(                 /* Name of sequencegenrator and allocationsize.*/
            name = "tasklist_sequence",
            sequenceName = "tasklist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(                                /*Connection to the sequence generator to decide sequence */
            strategy = GenerationType.SEQUENCE,
            generator = "tasklist_sequence"
    )
    @Column(                                /* Column annotation that will map object into a column in the database.*/
            name ="id",
            updatable = false
    )
    private Long id;
    @Column(                                /* Column annotation that will map object into a column in the database.*/
            name = "task",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String task;
    @Column(                                /* Column annotation that will map object into a column in the database.*/
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(                                /* Column annotation that will map object into a column in the database.*/
            name = "date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDate date;

    /*Enumeration to give "priority" a String value of low, mid and high to the database.*/

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Priority {
        LOW,
        MID,
        HIGH
    }

    /*Enumeration to give "status" a String value of active and inactive to the database.*/

    @Enumerated(EnumType.STRING)

    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE
    }
    @Column(
            name = "dueDate",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDate dueDate;


    /* Constructor with getters and setters and a toString. */

    public TodoList() {

    }
    public TodoList(String task,
                    String description,
                    LocalDate date,
                    Priority priority,
                    Status status,
                    LocalDate dueDate) {
        this.task = task;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
    }
    public boolean isDueTomorrow() {
        LocalDate today = LocalDate.now();
        return dueDate.isBefore(today);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.date = dueDate;
    }


    @Override
    public String toString() {
        return "Todolist{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", priority=" + priority +
                ", status=" + status +
                ", duedate=" + dueDate +
                '}';
    }
}
