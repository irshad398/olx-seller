import {Component, OnInit} from "@angular/core";
import {LoginService} from "../login/login.service";

@Component({
  selector:'seller-home',
  templateUrl :'./navigation.component.html',


})
export class NavigationComponent implements OnInit{

  title = 'Seller Home';
  public user;
  msg:any;
  constructor(private loginService:LoginService){  }
  ngOnInit(): void {
    this.loginService.getUserDetails().then((response ) => {
      this.user = response;
      console.log(response)
    }).catch((error) =>
    {
      console.log("Error");
    });
  }
  logout(){

    this.msg=this.loginService.logout();
  }
}
