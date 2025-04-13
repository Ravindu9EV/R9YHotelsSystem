import { Component } from '@angular/core';
import { BookingformComponent } from '../../components/bookingform/bookingform.component';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bookingpage',
  imports: [BookingformComponent, FormsModule, CommonModule],
  templateUrl: './bookingpage.component.html',
  styleUrl: './bookingpage.component.css',
})
export class BookingpageComponent {
  constructor(private http: HttpClient) {}

  public txtSearch: string = '';

  search() {
    if (this.txtSearch) {
      console.log(this.txtSearch);
    }
    console.log('hi');
  }
}
