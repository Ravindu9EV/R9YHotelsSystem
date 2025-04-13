import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Booking } from '../../model/Booking';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-bookingform',
  imports: [CommonModule, FormsModule],
  templateUrl: './bookingform.component.html',
  styleUrl: './bookingform.component.css',
})
export class BookingformComponent {
  constructor(private http: HttpClient) {}

  //public booking: Booking = new Booking(0, '', '', '', 0, 0, '');

  public fromDate: string = '';
  public toDate: string = '';
  public bookingType: string = '';
  public numberOfPeople: number = 0;
  public price: number = 0;
  public description: string = '';
  public booking: Booking = new Booking(
    0,
    this.fromDate,
    this.toDate,
    this.bookingType,
    this.numberOfPeople,
    this.price,
    this.description
  );

  book() {
    this.booking.setFromDate(this.fromDate);
    this.booking.setToDate(this.toDate);
    this.booking.setBookingType(this.bookingType);
    this.booking.setNumberOfPeople(this.numberOfPeople);
    this.booking.setPrice(this.price);
    this.booking.setDescription(this.description);
    console.log(this.booking);

    if (
      this.booking.getFromDate() === '' ||
      this.booking.getToDate() === '' ||
      this.numberOfPeople <= 0 ||
      this.booking.getBookingType() === '' ||
      this.price <= 0 ||
      this.booking.getDescription() === ''
    ) {
      alert('Empty');
    } else {
      console.log(this.booking);
      const token = localStorage.getItem('jwtToken');
      console.log(token);

      this.http
        .post<any>('http://localhost:8080/api/booking/save', this.booking, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .subscribe(
          (data) => {
            console.log(data);
          }
        );
    }
  }

  showV() {
    console.log(this.bookingType);
  }
}
