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
  constructor(private userData: TaskService, public dialog: MatDialog) {
    this.userData.viewTask().subscribe((data) => {
      console.warn('data', data);
    });
  }
  openDialog() {
    const dialogRef = this.dialog.open(EditTaskComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }
  moveTaskTo(arg0: string) {
    throw new Error('Method not implemented.');
  }
}
