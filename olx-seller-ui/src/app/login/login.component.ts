import { Component } from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login-form',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent {
  userLogin={
    name:'',
    password:''
  }
  constructor(private _loginService :LoginService, private router : Router){}
  login(){
    this._loginService.submitLoginData(this.userLogin)
      .subscribe(data=>{
          alert("Login Successfull!'");
          this.router.navigate(['/home']);
        },
        error=>{
          alert("Invalid credentials!! Please enter correct login details");
          this.router.navigate(['/home']);
          }
      );
  }
}
