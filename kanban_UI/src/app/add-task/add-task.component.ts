import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TaskService } from '../services/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {
onSubmit(_t7: NgForm) {
throw new Error('Method not implemented.');
}

constructor(private TaskService:TaskService, private router:Router) {}
taskData:any={};
minDate: Date = new Date();




addTask(){
  this.TaskService.addTask(this.taskData).subscribe(
    resp =>{
      console.log(resp);
      alert("Task Added Successfully");
      this.router.navigate(['dashboard']);
    }
  )
}

}
