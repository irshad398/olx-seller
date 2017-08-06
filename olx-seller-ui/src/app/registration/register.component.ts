import {Component} from "@angular/core";
import {Http,Response} from "@angular/http";
import {ActivatedRoute, Params, Router} from "@angular/router"
import 'rxjs/add/operator/map';

@Component({
  selector:'app-register',
  templateUrl :'./register.component.html',
  styleUrls :['./register.component.css']
})
export class RegisterComponent{
  title="registration";
  user;

  // constructor(private _user :RegisterService,private router : Router){}

  onRegister(user){
    console.log(user);
  }
}
