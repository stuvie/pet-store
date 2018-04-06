import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Pet } from '../models/pet';
import 'rxjs/add/operator/map';

@Injectable()
export class PetService {

  constructor(private http: HttpClient) { }

  petServiceUrl = 'http://steve-pet-services.cfapps.io/apis/v1/pets';

  getPets():Observable<Pet[]> {
    return this.http.get<Pet[]>(this.petServiceUrl).map((value, index) => {
      console.log('PetService got', value);
      
      return value.map((petData) => {
          const pet = new Pet();
          pet.name = petData.name;
          pet.status = petData.status;
          pet.category = petData.category;
          pet.photoUrl = petData.photoUrl;
          return pet;
      });
    });
  }

}
