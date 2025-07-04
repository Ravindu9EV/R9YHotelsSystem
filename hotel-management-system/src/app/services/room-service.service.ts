import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Room } from '../model/Room';

@Injectable({
  providedIn: 'root',
})
export class RoomServiceService {
  private apiUrl = 'http://127.0.0.1:8080/api/rooms';
  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
      throw new Error('No authentication token available');
    }
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  //handle error message
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An Unknown error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      if (error.error && error.error.message) {
        errorMessage = error.error.message;
      }
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
  //get all Rooms
  getAllRooms(): Observable<Room[]> {
    return this.http
      .get<Room[]>(this.apiUrl + '/all', {
        headers: this.getAuthHeaders(),
      })
      .pipe(map((rooms) => rooms.map((room) => Room.fromJson(room))));
  }

  //get available rooms
  getAvailableRooms(): Observable<Room[]> {
    return this.http
      .get<Room[]>(this.apiUrl + '/available-rooms', {
        headers: this.getAuthHeaders(),
      })
      .pipe(map((rooms) => rooms.map((room) => Room.fromJson(room))));
  }

  //filter rooms by price
  filterRoomsByPrice(price: number): Observable<Room[]> {
    return this.http
      .get<Room[]>(this.apiUrl + `/filter-by-price/${price}`, {
        headers: this.getAuthHeaders(),
      })
      .pipe(map((rooms) => rooms.map((room) => Room.fromJson(room))));
  }
  //Get Room By Id
  getRoomById(id: number): Observable<Room> {
    return this.http.get<Room>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }
  //handle text response
  private handleTextResponse(response: string): Room | string {
    console.log('Server response: ', response);
    return response;
  }

  //add new Room
  addRoom(room: Room): Observable<Room> {
    console.log('request: ' + room);
    console.log(localStorage.getItem('jwtToken'));

    return this.http
      .post(this.apiUrl + '/save', room, {
        headers: this.getAuthHeaders(),
        responseType: 'text',
      })
      .pipe(
        map(() => room),
        catchError(this.handleError)
      );
  }

  //update a room
  updateRoom(room: Room): Observable<Room> {
    return this.http.put<Room>(`${this.apiUrl}/${room.getId()}`, room, {
      headers: this.getAuthHeaders(),
    });
  }

  //delete room by id
  deleteRoom(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }
}
