import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {LoginService} from "./login/login.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router,private loginService: LoginService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
//write logic to check session
    if(this.loginService.isValidUser()){
      console.log("Logged in!")
      return true;
    }
  else{
      this.router.navigate(['/login']);
      console.log("Not logged in!")
      return false;
    }
  }
}
