import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  public register(userData: any){
    return this.http.post(`http://localhost:9000/api/v1/register`,userData);
    
  }

  public getAllUserEmailAndRole(){
    return this.http.get(`http://localhost:9000/api/v1/user/users`);
  }

  public getAllUsers(){
    return this.http.get(`http://localhost:9000/api/v1/user/getAllUsers`);
  }
  
  public getUserName(){
    return this.http.get(`http://localhost:9000/api/v1/user/getUsername`,{responseType:'text'});
  }

  
  public getUserRole(){
    return this.http.get(`http://localhost:9000/api/v1/user/getUserRole`,{responseType:'text'});
  }


  public sendMail(mailBody:object){
    
    return this.http.post(`http://localhost:8080/mail/sendEmail`,mailBody);
  }
}
