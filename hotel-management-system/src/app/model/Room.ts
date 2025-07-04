export class Room {
  private id: number;
  private roomNumber: string;
  private isBooked: boolean;
  private maxOccupancy: number;
  private price: number;

  constructor(
    id: number,
    roomNumber: string,
    isBooked: boolean,
    maxOccupancy: number,
    price: number
  ) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.isBooked = isBooked;
    this.maxOccupancy = maxOccupancy;
    this.price = price;
  }

  public setId(id: number) {
    this.id = id;
  }

  public setRoomNumber(roomNumber: string) {
    this.roomNumber = roomNumber;
  }

  public setIsBooked(isBooked: boolean) {
    this.isBooked = isBooked;
  }
  public setMaxOccupancy(maxOccupancy: number) {
    this.maxOccupancy = maxOccupancy;
  }
  public setPrice(price: number) {
    this.price = price;
  }

  public getId(): number {
    return this.id;
  }
  public getRoomNumber(): string {
    return this.roomNumber;
  }
  public getIsBooked(): boolean {
    return this.isBooked;
  }
  public getPrice(): number {
    return this.price;
  }
  public getMaxOccupancy(): number {
    return this.maxOccupancy;
  }

  static fromJson(json: any) {
    return new Room(
      json.id,
      json.roomNumber,
      json.isBooked,
      json.maxOccupancy,
      json.price
    );
  }
}
