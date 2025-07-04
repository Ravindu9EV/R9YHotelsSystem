import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CustomerFormComponent } from '../customer-form/customer-form.component';

@Component({
  selector: 'app-customer-registration',
  imports: [CustomerFormComponent],
  templateUrl: './customer-registration.component.html',
  styleUrl: './customer-registration.component.css',
})
export class CustomerRegistrationComponent {
  loading = false;
  error?: string;

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  onFormSubmit(formData: FormData): void {
    this.loading = true;
    this.error = undefined;

    this.customerService.registerCustomer(formData).subscribe({
      
      
      next: (customer) => {
        console.log(customer);

        this.router.navigate(['/customers', customer.getId()]);
      },
      error: (err) => {
        this.error =
          err.error?.message || 'Registration failed. Please try again';
        this.loading = false;
      },
    });
  }
}
