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
      return value.map((data) => {
        return new Pet(data);
      });
    });
  }

  getPet(id):Observable<Pet> {
    const petUrl = `${this.petServiceUrl}/${id}`
    return this.http.get<Pet>(petUrl).map((data) => {
      var re = /b.jpg/;
      data.photoUrl = data.photoUrl.replace(re, "h.jpg");
      return new Pet(data);
    });
  }

  deletePet(id):Observable<Pet> {
    const petUrl = `${this.petServiceUrl}/${id}`
    return this.http.delete<Pet>(petUrl).map((data) => {
      return new Pet(data);
    });
  }

  createPet(petData):Observable<Pet> {
    return this.http.post<Pet>(this.petServiceUrl, petData).map((data) => {
      return new Pet(data);
    });
  }

}
