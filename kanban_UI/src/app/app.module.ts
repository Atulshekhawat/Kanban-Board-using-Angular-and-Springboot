import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeSectionComponent } from './home-section/home-section.component';
import { HeaderSectionComponent } from './header-section/header-section.component';
import { FooterSectionComponent } from './footer-section/footer-section.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { HeroSectionComponent } from './hero-section/hero-section.component';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AddTaskComponent } from './add-task/add-task.component';
import { MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
import {MatMenuModule} from '@angular/material/menu';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import { HttpIntercepterService } from './services/http-intercepter.service';
import { EditTaskComponent } from './edit-task/edit-task.component';
import {MatRadioModule} from '@angular/material/radio';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { AboutUsComponent } from './about-us/about-us.component';
import { CommonModule } from '@angular/common';
import { ToastrModule } from 'ngx-toastr';
import {MatTooltipModule} from '@angular/material/tooltip';

@NgModule({
  declarations: [
    AppComponent,
    HomeSectionComponent,
    HeaderSectionComponent,
    FooterSectionComponent,
    UserLoginComponent,
    UserRegisterComponent,
    HeroSectionComponent,
    DashboardComponent,
    AddTaskComponent,
    EditTaskComponent,
    AboutUsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatCardModule,
    MatRadioModule,
    FormsModule,
    MatSelectModule,
    MatMenuModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    DragDropModule,
    MatDialogModule,
    CommonModule,
    ToastrModule.forRoot(),
    MatTooltipModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
