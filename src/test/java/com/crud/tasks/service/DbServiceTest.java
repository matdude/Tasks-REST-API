package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @Autowired
    private DbService service;

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Task 1", "Description 1");
        //When
        service.saveTask(task);
        Long id = service.getAllTasks().get(0).getId();
        //Then
        assertTrue(service.getTask(id).isPresent());
        //Clean
        service.deleteTaskById(id);
    }

    @Test
    public void testGetTaskById() {
        //Given
        Task task = new Task(1L, "Task 1", "Description 1");
        //When
        service.saveTask(task);
        Long id = service.getAllTasks().get(0).getId();
        Optional<Task> taskFromDB = service.getTask(id);
        //Then
        assertTrue(taskFromDB.isPresent());
        assertEquals(id, taskFromDB.get().getId());
        //Clean
        service.deleteTaskById(id);
    }

    @Test
    public void testGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Description 1");
        Task task2 = new Task(2L, "Task 2", "Description 2");
        //When
        service.saveTask(task1);
        service.saveTask(task2);
        Long id1 = service.getAllTasks().get(0).getId();
        Long id2 = service.getAllTasks().get(1).getId();
        List<Task> taskList = service.getAllTasks();
        //Then
        assertEquals(2, taskList.size());
        assertEquals(id1, taskList.get(0).getId());
        assertEquals(id2, taskList.get(1).getId());
        //Clean
        service.deleteTaskById(id1);
        service.deleteTaskById(id2);
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Description 1");
        Task task2 = new Task(2L, "Task 2", "Description 2");
        //When
        service.saveTask(task1);
        service.saveTask(task2);
        Long id1 = service.getAllTasks().get(0).getId();
        Long id2 = service.getAllTasks().get(1).getId();
        List<Task> taskListBeforeDelete = service.getAllTasks();
        service.deleteTaskById(id1);
        service.deleteTaskById(id2);
        List<Task> taskListAfterDelete = service.getAllTasks();
        //Then
        assertEquals(2, taskListBeforeDelete.size());
        assertTrue(taskListAfterDelete.isEmpty());
    }
}
