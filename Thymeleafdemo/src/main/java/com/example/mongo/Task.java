package com.example.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="tasks")
public class Task {
 @Id
 private int id;
 private String taskName;
 private String taskDescription;
 private String taskPriority;
 private String taskStatus;
 private int taskArchived = 0;
 public int getId() {
  return id;
 }
 public void setId(int taskId) {
  this.id = taskId;
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
 public String getTaskPriority() {
  return taskPriority;
 }
 public void setTaskPriority(String taskPriority) {
  this.taskPriority = taskPriority;
 }
 public String getTaskStatus() {
  return taskStatus;
 }
 public void setTaskStatus(String taskStatus) {
  this.taskStatus = taskStatus;
 }
 public int isTaskArchived() {
  return taskArchived;
 }
 public void setTaskArchived(int taskArchived) {
  this.taskArchived = taskArchived;
 }
 @Override
 public String toString() {
  return "Task [id=" + id + ", taskName=" + taskName
    + ", taskDescription=" + taskDescription + ", taskPriority="
    + taskPriority + ",taskStatus=" + taskStatus + "]";
 }
}

