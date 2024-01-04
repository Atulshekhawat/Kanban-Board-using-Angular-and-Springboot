import { Component } from '@angular/core';
import { TaskService } from '../services/task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent {
  minDate: Date = new Date();
  taskData:any={};
  editStatus: boolean = true;
  canDeactivate() {
    if (!this.editStatus) {
      this.editStatus = confirm("Do you still want to leave?");
    }
    return this.editStatus;
  }
  constructor(private taskService:TaskService,private activatedRoute: ActivatedRoute, private router:Router,private toastr:ToastrService) {}


  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let taskId = params.get("id") ?? 0;
      this.taskService.getSingleTask(taskId).subscribe((response: any) => {
        this.taskData = response.taskslist[0];
        console.log(response.taskslist[0]);
        this.editStatus = false;
        
      })
    })
  }


  updateTask(){
    this.taskService.updateTask(this.taskData).subscribe(
      (resp)=>{
        this.taskData=resp;
        this.toastr.success("Task Updated Successfully");
        this.router.navigate(['dashboard']);
        this.editStatus = true;
    },
    (err)=>{
      this.toastr.error("Task Not Updated Something Went Wrong");
    }
    )
  }
}
