import { Component } from '@angular/core';
import { RoomsCardComponent } from '../../components/rooms-card/rooms-card.component';

@Component({
  selector: 'app-roomspage',
  imports: [RoomsCardComponent],
  templateUrl: './roomspage.component.html',
  styleUrl: './roomspage.component.css',
})
export class RoomspageComponent {}
