import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from '../add-task/add-task.component';
import { TaskService } from '../services/task.service';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  data: Array<any> = [];
  currentTask: any;
  userName='';
  constructor(public dialog: MatDialog, private userData: TaskService,private userService:UserServiceService) {}

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
    this.userData.viewTask().subscribe((data: any) => {
      this.data = data;
      console.log(data);
    });
  }
  // Count Number of tasks
  getTasksCount(column: string): number {
    return this.filterTasks(column).length;
  }

  // filter the task data (By status)
  filterTasks(status: string) {
    return this.data?.filter((m) => m.status == status);
  }

  deleteTask(id: any) {
    this.userData.deleteTask(id).subscribe(
      (resp) => {
        console.log(resp);
        alert("Are You sure ")
        this.refreshTask();
      },
      (err) => {
        console.log(err);
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
      this.userData.updateTask(updatedStatus).subscribe(
        (response) => {
          console.log('Status updated successfully', response);
      }
    )}
    // this.currentTask = null;
  }

  onDragOver(event: any) {
    event.preventDefault();
  }
}
