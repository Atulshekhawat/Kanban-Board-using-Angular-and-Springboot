import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TaskService } from '../services/task.service';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {


constructor(private TaskService:TaskService, private router:Router,private userService:UserServiceService, private toastr:ToastrService ) {}
taskData:any={status:"To do"};
minDate: Date = new Date();

addTask(){
  this.TaskService.addTask(this.taskData).subscribe(
    
    resp =>{
      console.log(resp);
      let mailBody={
        recipient:sessionStorage.getItem('userEmail'),
        msgBody:`Dear User,
        A new task has been added by you in Kanban Board! 
        Details of the task:
        - Task Title: ${this.taskData.taskName}
        - Description: ${this.taskData.taskDescription}
        - Assigned To: ${this.taskData.assignedTo}
        - Priority : ${this.taskData.priority}
        - Due Date: ${this.taskData.dueDate}
        
        Please check your Kanban Board for more details.
        
        Best regards,
        Atul Singh
        KanBan Board Pvt. Ltd.`,
        subject: 'Task Added Successfully'
      } 
      this.userService.sendMail(mailBody).subscribe((resp)=>{
        console.log(resp);
      });
      this.toastr.success("Task Added Successfully")
      this.router.navigate(['dashboard']);
    },
    (err)=>{
      this.toastr.error("Task Not Added Something went wrong");
    }
  )
}

}
