import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {LoginService} from "./login/login.service";

@Injectable()
export class AuthGuard implements CanActivate {
  private isValidUser;
  private errorMsg;
  private flag;
  constructor(private router: Router, private loginService: LoginService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    /*if (this.loginService.isValidUser()) {
      console.log("Logged in already!");
      // this.router.navigate([''])
      return true;
    }
    else {
      console.log("Not logged in!");
      this.router.navigate(['/login']);
      return false;
    }*/

   /* this.loginService.checkSession().subscribe(data=>{
      if(data){
        this.isValidUser=true;
      }
      else{
        this.isValidUser=false;
      }
    });
    return this.isValidUser;
  }*/

    return new Promise<boolean>((resolve, reject) =>this.loginService.checkSession()
      .subscribe(data => {
          this.flag = data;
          if (this.flag === true)
          {

          }
          else if (this.flag === false) {
            alert('please login ..');
            this.router.navigate(['/login']);
          }
          resolve(true)
        }
        ,
        dataError => {
          this.errorMsg = dataError;
          resolve(false)
        }
      )
    )
  }
}
