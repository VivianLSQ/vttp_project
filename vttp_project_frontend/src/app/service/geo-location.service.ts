import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '../model/locations';

@Injectable({
  providedIn: 'root'
})
export class GeoLocationService {

  constructor(private http : HttpClient) { }
  private readonly apiURL = '/api/geolocation';

  createLocation(location: Location): Observable<Location> {
    return this.http.post<Location>('${this.apiURL}', location);
  }

  getLocationById(location: Location): Observable<Location>{
    return this.http.get<Location>('${this.apiURL}/${id}')
  }

  updateLocation(id: Long, location: Location): Observable<Location> {
    return this.http.put<Location>('${this.apiURL}/${id}', location);
  }

  deleteLocation(id: Long): Observable<Location> {
    return this.http.delete<Location>('${this.apiURL}/${id}');
  }

}
