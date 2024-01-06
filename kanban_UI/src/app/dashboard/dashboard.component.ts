import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TaskService } from '../services/task.service';
import { UserServiceService } from '../services/user-service.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  taskData: Array<any> = [];
  allUsersData:Array<any> = [];
  currentTask: any;
  userName='';
  UserRole='';
  sessionMail:any=sessionStorage.getItem('userEmail')



  constructor(public dialog: MatDialog, private taskservice: TaskService,private userService:UserServiceService,private toastr:ToastrService) {}

  ngOnInit() {
    this.refreshTask();
    this.allusers();

    console.log(this.sessionMail);
    this.userService.getUserName().subscribe((resp)=>{
      this.userName=resp;
      console.log(resp);
    });

    this.userService.getUserRole().subscribe((resp)=>{
      this.UserRole=resp;
      console.log(resp);
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
    this.taskservice.viewTask().subscribe((taskData: any) => {
      this.taskData = taskData;
      console.log(taskData);
    });
  }

  // Count Number of tasks
  getTasksCount(column: string): number {
    const filteredTasks = this.filterTasks(column);
    return filteredTasks ? filteredTasks.length : 0;
  }

  // filter the task data (By status)
  filterTasks(status: string) {
    return this.taskData?.filter((m) => m.status == status);
  }
  // Filter the task data for all users (By status)
  filterAllUserTasks(status: string) {
    return this.allUsersData?.filter((c) => c.status == status);
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

  onDragStart(task: any) {
    this.currentTask = task;
  }

  // Drag and Drop
  onDrop($event: any, status: string) {
    if(this.UserRole == 'Team Leader'){
      const updatedStatus = this.taskData.find((m) => m.taskId == this.currentTask.taskId);
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

    if(this.UserRole == 'Team Member'){
      const updatedStatus = this.allUsersData.find((m) => m.taskId == this.currentTask.taskId);
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
  }

  onDragOver(event: any) {
    event.preventDefault();
  }



  // _-------------------------?

  allusers(){
    this.userService.getAllUsers().subscribe((allUsersData:any)=>{
      this.allUsersData=allUsersData[0].taskslist;
      console.log(this.allUsersData);
    })
  }


}
