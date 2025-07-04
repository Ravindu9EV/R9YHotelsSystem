import { Component } from '@angular/core';
import { Customer } from '../../model/Customer';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './registration-form.component.html',
  styleUrl: './registration-form.component.css',
})
export class RegistrationFormComponent {
  constructor(private http: HttpClient) {}
  public username: string = '';
  public password: string = '';
  public email: string = '';
  public phone: string = '';
  public address: string = '';
  public age: number = 0;
  public customer: Customer = new Customer(
    0,

    '',
    '',
    '',
    '',
    '',
    '',
    0,
    new File([], '')
  );

  setData() {
    // this.customer.setUsername(this.username);
    this.customer.setPassword(this.password);
    this.customer.setEmail(this.email);
    this.customer.setPhone(this.phone);
    this.customer.setAddress(this.address);
    this.customer.setAge(this.age);
  }

  register() {
    this.setData();
    if (
      //this.customer.getUsername != null ||
      //this.customer.getUsername() != '' ||
      (this.customer.getPassword() != null ||
        this.customer.getPassword() != '' ||
        this.customer.getEmail() != null ||
        this.customer.getEmail() != '',
      this.customer.getPhone() != null ||
        this.customer.getPhone() != '' ||
        this.customer.getAddress() != null ||
        this.customer.getAddress() != '' ||
        this.customer.getAge() != null ||
        this.customer.getAge() > 0)
    ) {
      this.http
        .post<any>('http://localhost:8080/customer/register', this.customer)
        .subscribe((data) => {
          console.log(data);
        });
    }
  }
}
