import { Component, NgModule } from '@angular/core';
import { Room } from '../../../model/Room';
import { RoomFormComponent } from '../../../components/room-form/room-form.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dashboard',

  imports: [CommonModule, RoomFormComponent],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
})
export class AdminDashboardComponent {
  isFormVisible = false;
  editingRoom?: Room;

  openRoomForm(room?: Room): void {
    this.editingRoom = room || undefined;
    this.isFormVisible = true;
  }
}
