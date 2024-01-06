import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeSectionComponent } from './home-section/home-section.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuard } from './services/auth.guard';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { CanDeactivateGaurdService } from './services/can-deactivate-gaurd.service';
import { AddTaskComponent } from './add-task/add-task.component';

const routes: Routes = [
  {
    path:"",component:HomeSectionComponent
  },
  {
    path:"about-us",component:AboutUsComponent
  },
  {
    path:"login",component:UserLoginComponent
  },
  {
    path:"register",component:UserRegisterComponent
  },
  {
    path:"dashboard",component:DashboardComponent,canActivate:[AuthGuard]
  },
  {
    path:"edit-task/:id",component:EditTaskComponent,canDeactivate:[CanDeactivateGaurdService]
  },
  {
    path:"dashboard/add-task",component:AddTaskComponent
  },
  {
    path:"**",component:PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
