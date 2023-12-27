import { Component } from '@angular/core';
import { TaskService } from '../services/task.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent {
  minDate: Date = new Date();
  taskData:any={};

  constructor(private taskService:TaskService,private activatedRoute: ActivatedRoute, private router:Router) {}


  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let taskId = params.get("id") ?? 0;
      this.taskService.getSingleTask(taskId).subscribe((response: any) => {
        this.taskData = response.taskslist[0];
        console.log(response.taskslist[0]);
        // this.editStatus = false;
        
      })
    })
  }


  updateTask(){
    this.taskService.updateTask(this.taskData).subscribe(
      (resp)=>{
        this.taskData=resp;
        console.log(resp);
        this.router.navigate(['dashboard']);
    })
  }
}
