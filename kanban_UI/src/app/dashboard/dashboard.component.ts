import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from '../add-task/add-task.component';
import { CdkDragDrop, CdkDropList, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
doneList: any;

constructor(public dialog: MatDialog){}

openDialog() {
  const dialogRef = this.dialog.open(AddTaskComponent);

  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
  });
}
todo = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];

done = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

drop(event: CdkDragDrop<string[]>) {
  if (event.previousContainer === event.container) {
    moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
  } else {
    transferArrayItem(
      event.previousContainer.data,
      event.container.data,
      event.previousIndex,
      event.currentIndex,
    );
  }
}
}