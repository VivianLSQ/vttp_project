import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { AlertComponent } from '../alert/alert.component';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit{
  @ViewChild('alertComponent') alertComponent!: AlertComponent;
  private authSvc = inject(AuthenticationService);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  registerForm!: FormGroup;


  ngOnInit() {
    this.registerForm = this.fb.group(
      { email: this.fb.control<string>('', [Validators.required,Validators.email, Validators.maxLength(50),
      ]),
        username: this.fb.control<string>('', [Validators.required, Validators.minLength(10), Validators.maxLength(30),
      ]),
      password: this.fb.control<string>('', [Validators.required, Validators.minLength(10), Validators.maxLength(50),
      ]) },
    );
  }

  onSubmit(){
    this.register(this.registerForm.value);
  }

  register(user: User){
    this.authSvc.register(user).pipe().subscribe({
      next: () => this.router.navigate(['/login']),
      error: (err) => {
        this.alertComponent.message = err.message;
      },
    });
  }

  get email() {
    return this.registerForm.get('email')!;
  }

  get username() {
    return this.registerForm.get('username')!;
  }

  get password() {
    return this.registerForm.get('password')!;
  }

}
