export class Customer {
  private id: number;
  private firstName: string;
  private lastName: string;
  //   private username: string;
  private password: string;
  private email: string;
  private phone: string;
  private address: string;
  private age: number;
  private profileImage: File;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    // username: string,
    password: string,
    email: string,
    phone: string,
    address: string,
    age: number,
    profileImage: File
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    //this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.age = age;
    this.profileImage = profileImage;
    //this.profileImageUrl=profileImageUrl;
  }

  public getId(): number {
    return this.id;
  }
  //   public getUsername(): string {
  //     return this.username;
  //   }
  public getPassword(): string {
    return this.password;
  }
  public getEmail(): string {
    return this.email;
  }
  public getPhone(): string {
    return this.phone;
  }
  public getAddress(): string {
    return this.address;
  }
  public getAge(): number {
    return this.age;
  }

  //   public setUsername(username: string) {
  //     this.username = username;
  //   }
  public setPassword(password: string) {
    this.password = password;
  }
  public setEmail(email: string) {
    this.email = email;
  }
  public setPhone(phone: string) {
    this.phone = phone;
  }
  public setAddress(address: string) {
    this.address = address;
  }
  public setAge(age: number) {
    this.age = age;
  }

  public getFirstName(): string {
    return this.firstName;
  }

  public getLastName(): string {
    return this.lastName;
  }

  public setFirstName(firstName: string) {
    this.firstName = firstName;
  }

  public setLastName(LastName: string) {
    this.lastName = LastName;
  }

  public getProfileImage(): File {
    return this.profileImage;
  }
  // public getProfileImageUrl():string{
  //     return this.profileImageUrl;
  // }

  public setProfileImage(profileImage: File) {
    this.profileImage = profileImage;
  }
  // public setProfileImageUrl(profileImageUrl:string){
  //     this.profileImageUrl=profileImageUrl;
  // }
}
