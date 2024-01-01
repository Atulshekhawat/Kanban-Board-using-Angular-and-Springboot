import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  // url="http://localhost:9000"
 
  constructor(private http:HttpClient) { }

// calling the server to generate tocken

  generateToken(credentials:any){
    return this.http.post(`http://localhost:9000/api/v2/login`,credentials,{
      responseType: "text"
    });
  }

 // user login
  loginUser(token: string){
    localStorage.setItem("token",token)
    return true;
  }

  // to check that user is login or not
  isloggedIn(){
    let token=localStorage.getItem("token");
    if(token == undefined || token===''||token==null){
      return false;
    }else{
      return true;
    }
  }

  // to logout user
  logout(){
    localStorage.removeItem('token');
    return true;
  }

  // for getting the token
  getToken(){
    return localStorage.getItem('token');
  }

}
