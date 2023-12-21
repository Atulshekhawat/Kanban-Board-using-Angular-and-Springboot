package com.niit.UserTaskService.repository;

import com.niit.UserTaskService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTaskRepository extends MongoRepository<User, String> {
}
