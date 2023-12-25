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

}
