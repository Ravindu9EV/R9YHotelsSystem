import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Room } from '../../model/Room';
import { RoomServiceService as RoomService } from '../../services/room-service.service';
@Component({
  selector: 'app-rooms-card',
  imports: [CommonModule, FormsModule],
  templateUrl: './rooms-card.component.html',
  styleUrl: './rooms-card.component.css',
})
export class RoomsCardComponent implements OnInit {
  // constructor(private http:HttpClient){}

  public rooms: Room[] = [];

  constructor(private roomService: RoomService) {}

  ngOnInit(): void {
    this.getRooms();
  }

  getRooms(): void {
    this.roomService.getAllRooms().subscribe({
      next: (data: Room[]) => {
        this.rooms = data;
      },
      error: (err) => {
        console.error('Error fetching rooms: ', err);
      },
    });
  }
}
