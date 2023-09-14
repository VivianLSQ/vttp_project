import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-geo-location',
  templateUrl: './geo-location.component.html',
  styleUrls: ['./geo-location.component.css']
})
export class GeoLocationComponent {

  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      const locationId = +params['id']; // Extract ID from route parameter
      // Use id to fetch and update Locations object
    });
  }

}

