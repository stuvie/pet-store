import { Component, OnInit } from '@angular/core';
import { ObservableMedia } from '@angular/flex-layout';
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/takeWhile";
import "rxjs/add/operator/startWith";

import { PetService } from '../../services/pet.service';
import { Pet } from '../../models/pet';

@Component({
  selector: 'app-listing',
  templateUrl: './listing.component.html',
  styleUrls: ['./listing.component.scss']
})
export class ListingComponent implements OnInit {

  title = 'Pet Shop Boys';
  public cols : number = 2;
  pets: Pet[] = [];

  constructor( 
    private petService: PetService,
    private observableMedia: ObservableMedia
  ) { }

  ngOnInit() {
    const breakpoints : { [ size : string ] : number } = { 
      ["xs"] : 1,
      ["sm"] : 2,
      ["md"] : 3,
      ["lg"] : 4,
      ["xl"] : 5
    };
    this.observableMedia.subscribe(x => this.cols = breakpoints[x.mqAlias]);

    this.petService.getPets().subscribe(
      (data) => {
        // success
        console.log('ListingComponent got', data);
        this.pets = data;
      },
      (error) => {
        // handle errors
      }
    )
  }
}
