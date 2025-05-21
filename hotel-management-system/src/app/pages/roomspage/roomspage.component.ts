import { Component } from '@angular/core';
import { RoomsCardComponent } from '../../components/rooms-card/rooms-card.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-roomspage',
  imports: [RoomsCardComponent,CommonModule,FormsModule],
  templateUrl: './roomspage.component.html',
  styleUrl: './roomspage.component.css',
})
export class RoomspageComponent {}
