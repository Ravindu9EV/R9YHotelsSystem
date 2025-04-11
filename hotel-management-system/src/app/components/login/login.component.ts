import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, RouterOutlet],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  public credentials: any = { username: '', password: '' };
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log(this.credentials.username + '--' + this.credentials.password);

    this.authService.login(this.credentials).subscribe((response) => {
      if (response.token) {
        this.authService.storeToken(response.token);
        this.router.navigate(['']);
        alert('login Success!');
        console.log(response.token);
      } else {
        console.log(response);
      }
    });
  }
  logout() {
    localStorage.removeItem('jwtToken');
    alert('log out');
    this.router.navigate(['/login']);
  }
}
