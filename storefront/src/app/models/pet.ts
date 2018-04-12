export class Pet {
  id: number;
  name: string;
  status: string;
  category: string;
  photoUrl: string;
  cardTitle: string;
  ctaText: string;
  ctaLink: string;

  constructor(data) {
    this.id = data.id;
    this.name = data.name;
    this.status = data.status;
    this.category = data.category;
    this.photoUrl = data.photoUrl;
    this.cardTitle = data.cardTitle ? data.cardTitle : null;
    this.ctaText = data.ctaText ? data.ctaText : null;
    this.ctaLink = data.ctaLink ? data.ctaLink : null;
  }
}
