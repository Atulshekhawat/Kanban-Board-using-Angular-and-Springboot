package com.niit.UserTaskService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Task Already Exists")
public class TaskAlreadyExistsException extends Exception {
}
