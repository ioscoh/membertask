package com.example.membertask.repository;

import com.example.membertask.enetity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByIsDeletedFalse();
    Optional<Task> findByIdAndIsDeletedFalse(Long id);
}
