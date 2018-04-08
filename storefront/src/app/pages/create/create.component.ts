import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

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

  proto = {
    name: 'kkk',
    status: '',
    category: '',
    photoUrl: ''
  };

  get diagnostic() { return JSON.stringify(this.proto); }

  myForm: FormGroup;
  name: FormControl;
  photoUrl: FormControl;
  category: FormControl;
  submitted = false;

  constructor(
    private petService: PetService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.name = new FormControl('', [Validators.required, Validators.minLength(3)]);
    this.photoUrl = new FormControl('', [Validators.required, Validators.minLength(3)]);
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
      this.submitted = true;
      // console.log('Form valid!', this.myForm.value.name);
      // console.log('Form:', this.myForm);
      const petData = this.myForm.value.name;
      petData.status = 'available';
      this.pet = new Pet(petData);
      // console.log(this.pet);
      this.petService.createPet(this.pet).subscribe(
        (data) => {
          // success
          this.proto = data;
          // console.log('CreateComponent got', data);
          // this.router.navigate(['/']);
        },
        (error) => {
          // handle errors
        }
      )
    }
  }
}
