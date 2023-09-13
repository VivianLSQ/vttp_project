import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-email-form',
  templateUrl: './email-form.component.html',
  styleUrls: ['./email-form.component.css']
})
export class EmailFormComponent implements OnInit{
onSubmit(arg0: any) {
throw new Error('Method not implemented.');
}
  FormGroup: any;
  constructor(private builder: FormBuilder) { }

  ngOnInit(): void {
    this.FormGroup = this.builder.group({
      EmailAddress: new FormControl('', [Validators.required, Validators.email]),
      Body: new FormControl('', [Validators.required])
    });
  }

}
