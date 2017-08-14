import {Component} from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login-form',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent {
  userLogin = {
    email: '',
    password: ''
  }

  constructor(private _loginService: LoginService, private router: Router) {
  }

  login() {
    this._loginService.submitLoginData(this.userLogin)
      .subscribe(data => {
        this._loginService.isLoggedIn.next(true);
        this._loginService.username.next(data.seller_name);
          alert("Login Successfull!'");
          //console.log(this._loginService.getUserDetails())
          this.router.navigate(['/home/products']);
        },
        error => {
          alert("Invalid credentials!! Please enter correct login details");
          this.router.navigate(['/login']);
        }
      );
  }
}
