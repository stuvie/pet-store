export class Pet {
  id: number;
  name: string;
  status: string;
  category: string;
  photoUrl: string;

  constructor() { }
  
  build(petData) {
    this.id = petData.id;
    this.name = petData.name;
    this.status = petData.status;
    this.category = petData.category;
    this.photoUrl = petData.photoUrl;
  }
}
