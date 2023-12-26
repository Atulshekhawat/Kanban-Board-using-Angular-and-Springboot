import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from '../add-task/add-task.component';
import { CdkDragDrop, CdkDropList, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskService } from '../services/task.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  data: Array<any> = [];

constructor(public dialog: MatDialog,private userData:TaskService){}

ngOnInit(){
  // this.userData.viewTask().subscribe((data:any) => {
  //   this.data=data;
  //   console.log(data);
  // });
  this.refreshTask();
}

refreshTask(){
  this.userData.viewTask().subscribe((data:any) => {
    this.data=data;
    console.log(data);
  });
}

// filter the task data (By status)
filterTasks(status: string){
  return this.data?.filter(m=>m.status==status);

}

deleteTask(id:any){
  this.userData.deleteTask(id).subscribe((resp)=>{
    console.log(resp);
    this.refreshTask();
  },
  (err)=>{
    console.log(err);
  }
  )
}

onDragStart(){

}



// ---------------------------------------------------------------//
// This is for Dialog
openDialog() {
  const dialogRef = this.dialog.open(AddTaskComponent);

  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
  });
}








}