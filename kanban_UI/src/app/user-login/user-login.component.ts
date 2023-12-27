import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent { 
  credentials={
    userEmail:'',
    password:''
  }

  constructor(private loginService:LoginService){}
  

  onSubmit(){
    if((this.credentials.userEmail !='' && this.credentials.password !='') && (this.credentials.userEmail!=null && this.credentials.password!=null)){
      // console.log("We have to submit the form to server");
      // token generate
      this.loginService.generateToken(this.credentials).subscribe(
        (resp:any) => {
          console.log(resp);
          this.loginService.loginUser(resp)
          sessionStorage.setItem('userEmail',this.credentials.userEmail);
          window.location.href="/dashboard"
        }
      )

    }else{
      alert("Fields can't be empty");
    }
  }
}
