import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {LoginService} from "./login/login.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private loginService: LoginService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.loginService.isValidUser()) {
      console.log("Logged in already!");
      // this.router.navigate([''])
      return true;
    }
    else {
      console.log("Not logged in!");
      this.router.navigate(['/login']);
      return false;
    }
  }
}
