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
}
