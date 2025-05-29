export class Room{
    private id:number;
    private roomNumber:string;
    private is_booked:boolean;
    private maxOccupancy:number;
    private price:number;

    constructor(id:number,roomNumber:string,is_booked:boolean,maxOccupancy:number,price:number){
        this.id=id;
        this.roomNumber=roomNumber;
        this.is_booked=is_booked;
        this.maxOccupancy=maxOccupancy;
        this.price=price;
    }

    public setId(id:number){
        this.id=id;
    }

    public setRoomNumber(roomNumber:string){
        this.roomNumber=roomNumber;
    }

    public setIs_booked(is_booked:boolean){
        this.is_booked=is_booked;
    }
    public setMaxOccupancy(maxOccupancy:number){
        this.maxOccupancy=maxOccupancy;
    }
    public setPrice(price:number){
        this.price=price;
    }

    public getId():number{
        return this.id;
    }
    public getRoomNumber():string{
        return this.roomNumber;
    }
    public getIs_booked():boolean{
        return this.is_booked;
    }
    public getPrice():number{
        return this.price;
    }
    public getMaxOccupancy():number{
        return this.maxOccupancy;
    }
}