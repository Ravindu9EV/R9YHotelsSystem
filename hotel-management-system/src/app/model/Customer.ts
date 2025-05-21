export class Customer{
private id:number;
private username:string;
private password:string;
private email:string;
private phone:string;
private address:string;
private age:number;

constructor(id:number,username:string,password:string,email:string,phone:string,address:string,age:number){
    this.id=id;
    this.username=username;
    this.password=password;
    this.email=email;
    this.phone=phone;
    this.address=address;
    this.age=age;
}

public getId():number{
    return this.id;

}
public getUsername():string{
    return this.username;
}
public getPassword():string{
    return this.password;
}
public getEmail():string{
    return this.email;
}
public getPhone():string{
    return this.phone;
}
public getAddress():string{
    return this.address;
}
public getAge():number{
    return this.age;
}

public setUsername(username:string){
    this.username=username;
}
public setPassword(password:string){
    this.password=password;
}
public setEmail(email:string){
    this.email=email;
}
public setPhone(phone:string){
    this.phone=phone;
}
public setAddress(address:string){
    this.address=address;
}
public setAge(age:number){
    this.age=age;
}

}