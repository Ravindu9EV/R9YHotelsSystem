import { Component } from '@angular/core';
import { LoginComponent } from '../../components/login/login.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-loginpage',
  imports: [LoginComponent],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.css',
})
export class LoginpageComponent {}
