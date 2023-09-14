import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { AlertComponent } from '../alert/alert.component';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit{

  private authSvc = inject(AuthenticationService);
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  loginForm!: FormGroup;
  @ViewChild('alertComponent') alertComponent!: AlertComponent;

  ngOnInit() {
    this.loginForm = this.fb.group(
      { email: this.fb.control<string>('', [Validators.required,Validators.email, Validators.maxLength(50),
      ]),
        username: this.fb.control<string>('', [Validators.required, Validators.minLength(10), Validators.maxLength(30),
      ]),
      password: this.fb.control<string>('', [Validators.required, Validators.minLength(10), Validators.maxLength(50),
      ]) },
    );
  }

  onSubmit() {
    this.authSvc.logout();
    return this.login(this.loginForm.value);
  }
  login(user: User) {
    this.authSvc.register(user).pipe().subscribe({
      next: (data: any) => this.router.navigate(['/']),
      error: (err) => {
        this.alertComponent.message = err.message;
      },
    });
  }

  get email() {
    return this.loginForm.get('email')!;
  }

  get username() {
    return this.loginForm.get('username')!;
  }

  get password() {
    return this.loginForm.get('password')!;
  }

}
