import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Customer } from '../../model/Customer';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';
import { F } from '@angular/cdk/keycodes';
import { min } from 'rxjs';
import { CustomerResponse } from '../../model/CustomerResponse';
@Component({
  selector: 'app-customer-form',
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.css',
})
export class CustomerFormComponent implements OnInit {
  @Input() customer?: CustomerResponse;
  @Input() isEditMode = false;
  @Input() isSubmitting = false;
  @Output() formSubmit = new EventEmitter<FormData>();

  customerForm: FormGroup;
  profileImage?: File;
  profileImagePreview?: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService
  ) {
    this.customerForm = this.fb.group({
      id: [0, Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      // username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      address: ['', Validators.required],
      age: [0, Validators.required, Validators.min(18)],
      profileImage: [null],
    });
  }

  ngOnInit(): void {
    if (this.customer) {
      this.customerForm?.patchValue({
        id: this.customer.getId(),
        firstName: this.customer.getFirstName(),
        lastName: this.customer.getLastName(),
        email: this.customer.getEmail(),
        phone: this.customer.getPhone(),
        password: this.customer.getPassword(),
        address: this.customer.getAddress(),
        age: this.customer.getAge(),
      });

      if (this.customer?.getProfileImage()) {
        this.profileImagePreview = this.customer.getProfileImage();
      }
    }

    if (this.isEditMode) {
      this.customerForm?.get('password')?.clearValidators();
    } else {
      this.customerForm
        ?.get('password')
        ?.setValidators([Validators.required, Validators.minLength(6)]);
    }
  }

  numberOnly(event: KeyboardEvent): boolean {
    const charCode = event.which ? event.which : event.keyCode;

    if (
      charCode > 31 &&
      (charCode < 48 || charCode > 57) &&
      charCode !== 8 &&
      charCode !== 9 &&
      charCode !== 13 &&
      charCode !== 37 &&
      charCode !== 39 &&
      charCode != 46
    ) {
      return false;
    }
    return true;
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      if (!file.type.match('image.*')) {
        alert('Only image files are allowed');
        return;
      }

      if (file.size > 5 * 1024 * 1024) {
        alert('File size must be less than 5MB');
        return;
      }

      this.profileImage = file;
      this.customerForm.patchValue({
        profileImage: file.name,
      });
      this.customerForm.get('profileImage')?.updateValueAndValidity();
      const reader = new FileReader();
      reader.onload = () => {
        this.profileImagePreview = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (this.customerForm?.invalid) {
      this.customerForm.markAllAsTouched();
      return;
    }

    if (this.isSubmitting) {
      return;
    }
    this.isSubmitting = true;

    const formData = new FormData();
    formData.append('id', this.customerForm.value.id.toString());
    formData.append('firstName', this.customerForm.value.firstName);
    formData.append('lastName', this.customerForm.value.lastName);
    formData.append('email', this.customerForm.value.email);
    formData.append('phone', this.customerForm.value.phone);
    formData.append('password', this.customerForm.value.password);
    formData.append('address', this.customerForm.value.address);
    formData.append('age', this.customerForm.value.age);
    // Object.keys(this.customerForm.value).forEach((key) => {
    //   if (key !== 'profileImage') {
    //     formData.append(key, this.customerForm.value[key]);
    //   }
    // });

    if (this.profileImage) {
      formData.append('profileImage', this.profileImage);
    }
    console.log('formData: ');
    this.customerService.registerCustomer(formData).subscribe({
      next: (response) => {
        alert('Saved!' + response);
        this.isSubmitting = false;
        this.formSubmit.emit();
      },
      error: (error) => {
        alert('Registration Error: ' + error);
        this.isSubmitting = false;
      },
    });
    for (let [key, val] of formData.entries()) {
      console.log(key, val);
    }
    console.log('customer Form: ' + this.customerForm);
    // this.formSubmit.emit(formData);
  }
}
