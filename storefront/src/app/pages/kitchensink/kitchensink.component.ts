import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-kitchensink',
  templateUrl: './kitchensink.component.html',
  styleUrls: ['./kitchensink.component.scss']
})
export class KitchensinkComponent implements OnInit {
  title = 'Kitchen Sink';

  constructor() { }

  ngOnInit() {
  }

}
