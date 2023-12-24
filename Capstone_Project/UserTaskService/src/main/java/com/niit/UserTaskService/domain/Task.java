package com.niit.UserTaskService.domain;

import org.springframework.data.annotation.Id;

import java.util.UUID;


public class Task {

   @Id
   private UUID taskId = UUID.randomUUID();
    private String taskName;
    private String taskDescription;
    private String assignee;
    private String dueDate;
    private String priority; //high,low,medium
    private String status;  //To Do, InProgress,Testing,Completed

    public Task() {
    }

    public Task(UUID taskId, String taskName, String taskDescription, String assignee, String dueDate, String priority, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", assignee='" + assignee + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
