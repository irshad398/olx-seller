import {Component, OnInit} from "@angular/core";
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'seller-home',
  templateUrl: './navigation.component.html',


})
export class NavigationComponent implements OnInit {

  title = 'Seller Home';
  public user;
  msg: any;
  username:string;
  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {
    this.loginService.getUserDetails().then((response) => {
      this.user = response;
      console.log(response)
    }).catch((error) => {
      console.log("Error");
    });
    // this.loginService.username.subscribe(data=>this.username=data);
    this.username=window.localStorage.getItem('user');
  }

  logout() {

    this.loginService.logout().subscribe(data => {
      this.msg = data
    }, err => {
      console.log("Error", err)
    });
    if (this.msg == true) {
      console.log("Logged out!");
      window.localStorage.clear();
    }
    this.router.navigate(['/login'])
  }
}
