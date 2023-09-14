import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent implements OnInit {
  constructor(private router: Router){};
  private authSvc = inject(AuthenticationService);

  ngOnInit() {
    this.logout();
    return this.router.navigate(['/login']);
  }

  logout() {
    this.authSvc.logout();
  }

}
