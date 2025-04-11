import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomspageComponent } from './roomspage.component';

describe('RoomspageComponent', () => {
  let component: RoomspageComponent;
  let fixture: ComponentFixture<RoomspageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomspageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomspageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
