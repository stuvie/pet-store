export class Pet {
  id: number;
  name: string;
  status: string;
  category: string;
  photoUrl: string;
  
  constructor(data) {
    this.id = data.id;
    this.name = data.name;
    this.status = data.status;
    this.category = data.category;
    this.photoUrl = data.photoUrl;
  }
}
