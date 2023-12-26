import { Component } from '@angular/core';
import { TaskService } from '../services/task.service';
import { EditTaskComponent } from '../edit-task/edit-task.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css'],
})
export class ViewTaskComponent {
  // public toDoData: any =  [];
  // public inProgressData: any =  [];
  // public completedData: any =  [];
  // public backLogData: any =  [];

  public data:any;
  constructor(private userData: TaskService, public dialog: MatDialog) {
    this.userData.viewTask().subscribe((data:any) => {

      this.data=data;
      // for(let d of data){
      //   if(d.status=="To do"){
      //     this.toDoData.push(data);
      //   }else if(d.status=="In Progress"){
      //     this.inProgressData.push(data);
      //   }else if(d.status=="Completed"){
      //     this.completedData.push(data);
      //   }else{
      //     this.backLogData.push(data);
      //   }
      //   }
      // }
      // console.log(this.toDoData);
      // console.log(this.inProgressData);
      // console.log(this.completedData);
      // console.log(this.backLogData);
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(EditTaskComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  editTask(){

  }
  moveTaskTo(arg0: string) {
    throw new Error('Method not implemented.');
  }
}
