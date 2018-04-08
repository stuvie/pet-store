import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { PetService } from '../../services/pet.service';
import { Pet } from '../../models/pet';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  title = 'New Pet Input Form';
  pet: Pet = null;
  categories = ['cat', 'dog', 'racoon'];
  myForm: FormGroup;
  name: FormControl;
  photoUrl: FormControl;
  category: FormControl;

  constructor() {
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.name = new FormControl('', Validators.required);
    this.photoUrl = new FormControl('', Validators.required);
    this.category = new FormControl('', Validators.required);
  }

  createForm() {
    this.myForm = new FormGroup({
      name: new FormGroup({
        name: this.name,
        photoUrl: this.photoUrl,
        category: this.category
      })
    });
  }

  onSubmit() {
    if (this.myForm.valid) {
      console.log('Form valid!', this.myForm.value.name);
      const petData = this.myForm.value.name;
      petData.status = 'available';
      this.pet = new Pet(petData);
      console.log(this.pet);
    }
  }
}
