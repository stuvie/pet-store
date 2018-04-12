import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PetCardComponent } from './pet-card.component';

describe('PetCardComponent', () => {
  let component: PetCardComponent;
  let fixture: ComponentFixture<PetCardComponent>;
  let pet = {id: 1, name: 'Spot', status: 'single', category: 'cat', photoUrl: 'google'};

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
      declarations: [ PetCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    pet = pet;
    fixture = TestBed.createComponent(PetCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(pet.name).toBe('Spot');
    expect(component).toBeTruthy();
  });
});
