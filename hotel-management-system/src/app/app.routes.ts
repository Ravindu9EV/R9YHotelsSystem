import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { authGuard } from './auth.guard';
import { BookingpageComponent } from './pages/bookingpage/bookingpage.component';
import { RoomspageComponent } from './pages/roomspage/roomspage.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { AdminDashboardComponent } from './pages/dashboard/admin-dashboard/admin-dashboard.component';
import { CustomerDetailsComponent } from './pages/customer-details/customer-details.component';
import { CustomerAccountComponent } from './pages/customer-account/customer-account.component';
import { EmployeeManageComponent } from './pages/employee-manage/employee-manage.component';

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
  {
    path: 'register',
    component: RegistrationFormComponent,
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: 'customer-det',
    component: CustomerDetailsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'customer-account/:id',
    // path: 'customer-account/:id',
    component: CustomerAccountComponent,
    canActivate: [authGuard],
  },
  {
    path: 'employee-manage',
    component: EmployeeManageComponent,
    canActivate: [authGuard],
  },
];
