import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Pet } from '../models/pet';
import 'rxjs/add/operator/map';

@Injectable()
export class PetService {

  constructor(private http: HttpClient) { }

  petServiceLocal = 'http://localhost:9999/apis/v1/pets';
  petServiceCloud = 'http://steve-pet-services.cfapps.io/apis/v1/pets';
  petServiceUrl = this.petServiceLocal;

  getPets():Observable<Pet[]> {
    return this.http.get<Pet[]>(this.petServiceUrl).map((value, index) => {
      return value.map((petData) => {
          const pet = new Pet();
          pet.id = petData.id;
          pet.name = petData.name;
          pet.status = petData.status;
          pet.category = petData.category;
          pet.photoUrl = petData.photoUrl;
          pet.largePhotoUrl = petData.photoUrl;
          return pet;
      });
    });
  }

  getPet(id):Observable<Pet> {
    const petUrl = `${this.petServiceUrl}/${id}`
    return this.http.get<Pet>(petUrl).map((petData) => {
        const pet = new Pet();
        pet.id = petData.id;
        pet.name = petData.name;
        pet.status = petData.status;
        pet.category = petData.category;
        var re = /b.jpg/; 
        pet.photoUrl = petData.photoUrl.replace(re, "h.jpg");
        return pet;
    });
  }

}
