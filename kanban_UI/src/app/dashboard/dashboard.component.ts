import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from '../add-task/add-task.component';
import { TaskService } from '../services/task.service';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { UserServiceService } from '../services/user-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  data: Array<any> = [];
  currentTask: any;
  userName='';
  constructor(public dialog: MatDialog, private taskservice: TaskService,private userService:UserServiceService,private toastr:ToastrService) {}

  ngOnInit() {
    // this.userData.viewTask().subscribe((data:any) => {
    //   this.data=data;
    //   console.log(data);
    // });
    this.refreshTask();
    this.userService.getUserName().subscribe((resp)=>{
      console.log(resp);
      this.userName=resp;
    });
  }

  // Color based on priority
  getBackgroundColor(priority: string): string {
    switch (priority) {
      case 'Low':
        return 'lightgreen';
      case 'Medium':
        return 'coral';
      case 'High':
        return 'lightcoral';
      default:
        return 'coral';
    }
  }

  refreshTask() {
    this.taskservice.viewTask().subscribe((data: any) => {
      this.data = data;
      console.log(data);
    });
  }

column:string=''
  // Count Number of tasks
  getTasksCount(column: string): number {
    const filteredTasks = this.filterTasks(column);
    return filteredTasks ? filteredTasks.length : 0;
  }

  // filter the task data (By status)
  filterTasks(status: string) {
    return this.data?.filter((m) => m.status == status);
  }

  deleteTask(id: any) {
    this.taskservice.deleteTask(id).subscribe(
      (resp) => {
        // console.log(resp);
        this.toastr.success("Task Deleted Successfully");
        this.refreshTask();
      },
      (err) => {
        // console.log(err);
        this.toastr.error("Something Went Wrong");
      }
    );
  }

  // This is for Dialog
  openDialog() {
    const dialogRef = this.dialog.open(AddTaskComponent);

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  onDragStart(task: any) {
    this.currentTask = task;
  }

  onDrop($event: any, status: string) {
    const updatedStatus = this.data.find((m) => m.taskId == this.currentTask.taskId);
    if (updatedStatus != undefined) {
      updatedStatus.status = status;
      this.taskservice.updateTask(updatedStatus).subscribe(
        (response) => {
          // console.log('Status updated successfully', response);
          this.toastr.success("Tasked Moved Successfully");
      }
    )}
    // this.currentTask = null;
  }

  onDragOver(event: any) {
    event.preventDefault();
  }
}
