import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Room } from '../../model/Room';
import { RoomServiceService as RoomService } from '../../services/room-service.service';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room-form',
  imports: [CommonModule, ReactiveFormsModule, RoomFormComponent],
  templateUrl: './room-form.component.html',
  styleUrl: './room-form.component.css',
})
export class RoomFormComponent implements OnInit {
  @Input() room?: Room;
  @Output() closeFormEvent = new EventEmitter<void>();
  @Output() roomUpdated = new EventEmitter<Room>();
  roomForm!: FormGroup;

  constructor(private fb: FormBuilder, private roomService: RoomService) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    this.roomForm = this.fb.group({
      id: [this.room?.getId() || null],
      roomNumber: [
        this.room?.getRoomNumber() || '',
        [Validators.required, Validators.maxLength(20)],
      ],
      is_booked: [this.room?.getIs_booked() || false],
      maxOccupancy: [
        this.room?.getMaxOccupancy() || 1,
        [Validators.required, Validators.min(0)],
      ],
      price: [
        this.room?.getPrice() || 0,
        [Validators.required, Validators.min(0)],
      ],
    });
  }

  submitForm(): void {
    if (this.roomForm.valid) {
      const formValue = this.roomForm.value;
      const room = new Room(
        formValue.id,
        formValue.roomNumber,
        formValue.is_booked,
        formValue.maxOccupancy,
        formValue.price
      );
      console.log(formValue);

      const operation = room.getId()
        ? this.roomService.updateRoom(room)
        : this.roomService.addRoom(room);

      operation.subscribe({
        next: (savedRoom: Room) => {
          const message = formValue.id
            ? 'Room updated successfully'
            : 'Room added successfully';
          alert(message);
          this.roomUpdated.emit(savedRoom);
          if (!room.getId()) {
            this.roomForm.reset();
          }
        },
        error: (err) => {
          console.error('Operation failed:', err);
          alert('Operation failed,Please try again.');
        },
      });
    }
  }

  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach((control) => {
      control.markAsTouched();
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }

  onCancel(): void {
    this.closeFormEvent.emit();
  }
}
