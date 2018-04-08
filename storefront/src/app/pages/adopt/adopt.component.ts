import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

import { PetService } from '../../services/pet.service';
import { Pet } from '../../models/pet';

@Component({
  selector: 'app-adopt',
  templateUrl: './adopt.component.html',
  styleUrls: ['./adopt.component.scss']
})
export class AdoptComponent implements OnInit {

  title = 'Thank You';
  pet: Pet = null;
  id: 0;

  constructor(
    private petService: PetService,
    private route: ActivatedRoute
  ) { 
    this.route.params.subscribe(
      params => {
        this.id = params.id;
      }
    );
  }

  ngOnInit() {
    this.petService.deletePet(this.id).subscribe(
      (data) => {
        // success
        console.log('AdoptComponent got', data);
        this.pet = data;
      },
      (error) => {
        // handle errors
      }
    )
  }
}
