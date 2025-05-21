import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from '../../components/login/login.component';
import { RoomFormComponent } from '../../components/room-form/room-form.component';
import { Room } from '../../model/Room';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, RoomFormComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  isFormVisible = false;
  editingRoom?: Room;

  openRoomForm(room?: Room): void {
    this.editingRoom = room || undefined;
    this.isFormVisible = true;
  }
}
