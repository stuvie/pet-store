import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

import { PetService } from '../../services/pet.service';
import { Pet } from '../../models/pet';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  title = 'Adopt this loving pet';
  pet: Pet = null;
  id: 0;

  constructor(
    private petService: PetService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(
      params => {
        console.log(params);
        this.id = params.id;
      }
    );
  }

  ngOnInit() {
    this.petService.getPet(this.id).subscribe(
      (data) => {
        // success
        console.log('ViewComponent got', data);
        this.pet = data;
      },
      (error) => {
        // handle errors
      }
    )
  }

}
