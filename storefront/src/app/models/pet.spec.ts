import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Pet } from './pet';

fdescribe('Pet', () => {
  let testPet: Pet;

  fit('should create', () => {
    let data = {
      id: 40,
      name: 'Spot',
      status: 'available',
      category: 'puppy',
      photoUrl: 'https://i.imgur.com/gM37Bi4b.jpg'
    };
    
    testPet = new Pet(data);
    expect(testPet.id).toEqual(40);
    expect(testPet.name).toEqual('Spot');
    expect(testPet.status).toEqual('available');
    expect(testPet.category).toEqual('puppy');
    expect(testPet.photoUrl).toEqual('https://i.imgur.com/gM37Bi4b.jpg');
  });
});
