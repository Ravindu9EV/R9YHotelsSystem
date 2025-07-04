import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../model/Customer';
import { CustomerRegistrationComponent } from '../../components/customer-registration/customer-registration.component';
import { CustomerFormComponent } from '../../components/customer-form/customer-form.component';
import { CustomerResponse } from '../../model/CustomerResponse';

@Component({
  selector: 'app-customer-details',
  imports: [CustomerFormComponent],
  templateUrl: './customer-details.component.html',
  styleUrl: './customer-details.component.css',
})
export class CustomerDetailsComponent implements OnInit {
  public customer?: Customer; //
  public customerRes?: CustomerResponse;
  loading = true;
  error?: string;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    // this.loadCustomer(id);
  }

  loadCustomer(id: number): void {
    this.loading = true;
    this.customerService.getCustomer(id).subscribe({
      next: (customer) => {
        this.customerRes = customer;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load customer details';
        this.loading = false;
        console.error(err);
      },
    });
  }
}
