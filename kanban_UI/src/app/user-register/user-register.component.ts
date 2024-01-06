import { Component } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {
  hide = true;
  

  constructor(private UserServiceService:UserServiceService,private toaster:ToastrService,private route:Router){}
  public userData  ={
    userEmail:"",
    userName:"",
    password:"",
    role:"",
    taskslist:null
  }

  register(){
    if(( this.userData.userName!='' && this.userData.userEmail !='' && this.userData.password !='' && this.userData.role!=='') && (this.userData.userName!=null && this.userData.userEmail!=null && this.userData.password!=null && this.userData.role!=null)){
    this.UserServiceService.register(this.userData).subscribe(
      (res) => {
        
        console.log(res);
        this.toaster.success("Register Successfully");
        this.route.navigate(['']);
        let mailBody = {
          recipient: this.userData.userEmail,
          msgBody: `
          Dear User,
                  
          Thank you for registering with our Kanban Board Service! Your registration was successful. You can now log in to your account and start managing your projects efficiently.
                  
          Best regards,
          Atul Singh
          KanBan Board Service
          `,
          subject: 'Register Successfully Team: KanBan Board'
        };
        // this.UserServiceService.sendMail(mailBody).subscribe((resp)=>{
        //   console.log(resp);
        // });
      },
      (err)=>{
        this.toaster.error("User Already Exist");
      }
  );
    }else{
      this.toaster.error("Fields cannot be empty");
    }
  }
}
