package com.FileWatcherService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FileWatcherService.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}