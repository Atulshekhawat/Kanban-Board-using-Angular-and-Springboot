import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  // url="";

  constructor(private http:HttpClient) { }

  public addTask(taskData:any){
    return this.http.post(`http://localhost:9000/api/v1/user/saveTask`,taskData);
  }
  
  public viewTask(){
    return this.http.get(`http://localhost:9000/api/v1/user/tasks`);
  }

  public updateTask(taskData:any){
    return this.http.put(`http://localhost:9000/api/v1/user/updateTask`,taskData);
  }

  public getSingleTask(id:any){
    return this.http.get(`http://localhost:9000/api/v1/user/get-task/${id}`);
  }

  public deleteTask(id:any){
    return this.http.delete(`http://localhost:9000/api/v1/user/deleteTask/${id}`);
  }
}
