import { Component } from '@angular/core';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {
  

  constructor(private UserServiceService:UserServiceService){}
  public userData  ={
    userEmail:"",
    userName:"",
    password:"",
    taskslist:null
  }
  register(){

    this.UserServiceService.register(this.userData).subscribe(
      res => {
        console.log(res);
        alert("User Register Successfully");
      }
    );

  }
}
