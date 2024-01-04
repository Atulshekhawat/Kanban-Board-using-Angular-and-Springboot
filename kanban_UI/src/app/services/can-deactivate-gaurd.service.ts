import { Injectable } from '@angular/core';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { EditTaskComponent } from '../edit-task/edit-task.component';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CanDeactivateGaurdService {

  canDeactivate(
    component: EditTaskComponent,
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return component.canDeactivate ? component.canDeactivate() : true;
  }
}
