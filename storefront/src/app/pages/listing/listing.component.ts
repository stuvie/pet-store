import { Component, OnInit } from '@angular/core';
import { PetService } from '../../services/pet.service';
import { Pet } from '../../models/pet';

@Component({
  selector: 'app-listing',
  templateUrl: './listing.component.html',
  styleUrls: ['./listing.component.scss']
})
export class ListingComponent implements OnInit {

  pets: Pet[] = [];

  constructor( private petService: PetService ) { }

  ngOnInit() {
    this.petService.getPets().subscribe(
      (data) => {
        // success
        console.log('HomepageComponent got', data);
        console.log('type: ', typeof data[0]);
        this.pets = data;
      },
      (error) => {
        // handle errors
      }
    )
  }
}
