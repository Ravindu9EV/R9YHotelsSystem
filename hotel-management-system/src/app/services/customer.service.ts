import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../model/Customer';
import { CustomerResponse } from '../model/CustomerResponse';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/api/customer';
  constructor(private http: HttpClient) {}

  registerCustomer(formData: FormData): Observable<any> {
    console.log('kjkk: ' + formData);

    return this.http.post<Customer>(`${this.apiUrl}/register`, formData);
  }

  getCustomer(id: number): Observable<CustomerResponse> {
    return this.http.get<CustomerResponse>(`${this.apiUrl}/${id}`);
  }

  getCustomerByEmail(email: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${email}`);
  }

  updateCustomer(id: number, formData: FormData): Observable<CustomerResponse> {
    return this.http.put<CustomerResponse>(`${this.apiUrl}/${id}`, formData);
  }
}
