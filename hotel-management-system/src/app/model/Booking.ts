export class Booking {
  private id: number;
  private bookingType: string;
  private fromDate: string;
  private toDate: string;
  private numberOfPeople: number;
  private price: number;
  private description: string;

  constructor(
    id: number,
    bookingType: string,
    fromDate: string,
    toDate: string,
    numberOfPeople: number,
    price: number,
    description: string
  ) {
    this.id = id;
    this.bookingType = bookingType;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.numberOfPeople = numberOfPeople;
    this.price = price;
    this.description = description;
  }
  public getId(): number {
    return this.id;
  }
  public getFromDate(): string {
    return this.fromDate;
  }
  public getToDate(): string {
    return this.toDate;
  }
  public getNumberOfPeople() {
    return this.numberOfPeople;
  }
  public getPrice(): number {
    return this.price;
  }
  public getBookingType(): string {
    return this.bookingType;
  }
  public getDescription(): string {
    return this.description;
  }

  public setId(id: number) {
    this.id = id;
  }

  public setFromDate(fromDate: string) {
    this.fromDate = fromDate;
  }
  public setToDate(toDate:string){
    this.toDate=toDate;
  }
  public setBookingType(bookingType:string){
    this.bookingType=bookingType;
  }
  public setNumberOfPeople(numberOfPeople:number){
    this.numberOfPeople=numberOfPeople;
  }
  public setPrice(price:number){
    this.price=price;
  }
  public setDescription(description:string){
    this.description=description;
  }
  
}
