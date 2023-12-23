import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-header-section',
  templateUrl: './header-section.component.html',
  styleUrls: ['./header-section.component.css']
})
export class HeaderSectionComponent {

  public loggedIn=false;

  constructor(private loginService:LoginService){}

  ngOnInit():void{
    this.loggedIn=this.loginService.isloggedIn();
  }

  logoutUser(){
    this.loginService.logout();
    location.reload();
  }
}
