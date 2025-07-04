import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Booking } from '../../model/Booking';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

interface RoomRates {
  standard: number;
  deluxe: number;
  suite: number;
  [key: string]: number;
}

interface PartySpaceRates {
  small: number;
  medium: number;
  large: number;
  [key: string]: number;
}

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
  public numberOfPeople: number = 1;
  public price: number = 0;
  public description: string = '';
  public userame: string = localStorage.getItem('username') || '';
  public calculatedPrice: number = 0;
  public priceBreakdown: string = '';

  private roomRates: RoomRates = {
    standard: 100,
    deluxe: 150,
    suite: 200,
  };

  private partySpaceRates: PartySpaceRates = {
    small: 300,
    medium: 500,
    large: 800,
  };

  public booking: Booking = new Booking(
    0,
    this.fromDate,
    this.toDate,
    this.bookingType,
    this.numberOfPeople,
    this.calculatedPrice,
    this.description,
    'PENDING',
    this.userame
  );

  calculatePrice() {
    console.log('cal');

    if (
      !this.fromDate ||
      !this.toDate ||
      !this.numberOfPeople ||
      !this.bookingType
    ) {
      this.calculatedPrice = 0;
      this.priceBreakdown = '';
      return;
    }
    console.log('begin');

    const nights = this.getNightsDifference();
    console.log('ni' + nights);

    if (nights <= 0) {
      console.log('hmmm');
      return;
    }
    if (this.bookingType === 'ROOM') {
      console.log('roooom');

      this.calculateRoomPrice(nights);
    } else {
      this.calculatePartySpacePrice(nights);
    }

    this.price = this.calculatedPrice;
    this.booking.setPrice(this.calculatedPrice);
  }

  private getNightsDifference(): number {
    const from = new Date(this.fromDate);
    const to = new Date(this.toDate);

    if (from >= to) {
      Swal.fire({
        icon: 'error',
        title: 'Invalid Dates',
        text: 'Check-out date must be after check-in date',
      });
      return 0;
    }
    const diffTime = Math.abs(to.getTime() - from.getTime());
    console.log('cel', Math.ceil(diffTime / (1000 * 60 * 60 * 24)));

    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  }

  private calculateRoomPrice(nights: number) {
    let roomType: keyof RoomRates = 'standard';

    if (this.numberOfPeople > 2 && this.numberOfPeople <= 4)
      roomType = 'deluxe';

    if (this.numberOfPeople > 4 && this.numberOfPeople <= 10)
      roomType = 'suite';

    console.log('nights:', nights);

    const rate = this.roomRates[roomType];
    this.calculatedPrice = rate * nights;
    this.priceBreakdown = `${rate} per night x ${nights} nights (${roomType} room)`;
  }

  private calculatePartySpacePrice(nights: number) {
    let spaceSize: keyof PartySpaceRates = 'small';
    if (this.numberOfPeople > 20 && this.numberOfPeople <= 50)
      spaceSize = 'medium';
    if (this.numberOfPeople > 50) spaceSize = 'large';

    const rate = this.partySpaceRates[spaceSize];
    this.calculatedPrice = rate * nights;
    this.priceBreakdown = `${rate} per day x ${nights} days (${spaceSize} space)`;
  }

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
      this.price <= 0
      // this.booking.getDescription() === ''
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
        .subscribe((data) => {
          if (data.message === 'saved Success!') {
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: data.message,
              showConfirmButton: false,
              timer: 1500,
            });
            console.log(data);
          } else {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: data.message,
              footer: '<a href="#">Why do I have this issue?</a>',
            });
          }
        });
    }
  }

  showV() {
    console.log('Hi');
    this.calculatePrice();
    console.log(this.price, this.calculatedPrice);
  }
}
