import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { authGuard } from './auth.guard';
import { BookingpageComponent } from './pages/bookingpage/bookingpage.component';
import { RoomspageComponent } from './pages/roomspage/roomspage.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginpageComponent,
  },
  {
    path: '',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: 'booking',
    component: BookingpageComponent,
    canActivate: [authGuard],
  },
  {
    path: 'rooms',
    component: RoomspageComponent,
    canActivate: [authGuard],
  },
];
