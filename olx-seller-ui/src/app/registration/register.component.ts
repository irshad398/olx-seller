import {Component} from "@angular/core";
import {Router} from "@angular/router"
import 'rxjs/add/operator/map';
import {RegisterService} from "./register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  title = "registration";
  user = {
    name: '',
    password: '',
    phoneNumber: '',
    email: '',
    question: '',
    answer: '',
  };

  constructor(private _registerService: RegisterService, private router: Router) {
  }

  public register(userData) {
    console.log(userData);
    this.user.name = userData.name;
    this.user.password = userData.password;
    this.user.phoneNumber = userData.phoneNumber;
    this.user.email = userData.email;
    this.user.question = userData.question;
    this.user.answer = userData.answer;
    userData = this.user;
    console.log(userData);
    this._registerService.submitUserData(userData)
      .subscribe(data => {
          if (data == true) {
            alert("Registration Success!'");
            this.router.navigate(['/login']);
          }
          else {
            alert("Already exists!...")
          }
        },
        error => {
          console.log("Registration failed")
        }
      );
  }

}
