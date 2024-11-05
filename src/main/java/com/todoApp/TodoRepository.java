package com.todoApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Connection and interaction with the database.*/
@Repository
public interface TodoRepository extends JpaRepository<TodoList, Long> {
}
