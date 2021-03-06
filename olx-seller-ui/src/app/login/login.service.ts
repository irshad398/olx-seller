import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Headers} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {isNullOrUndefined} from "util";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class LoginService {
  private userDetails: {
    seller_id: number,
    seller_name: string,
    email: string;
    password: string;

  };
  isLoggedIn:BehaviorSubject<any> = new BehaviorSubject(false);
  username:BehaviorSubject<any> = new BehaviorSubject("");

  private _loginUrl: string = "http://localhost:8080/olx-seller-1/user/login";
  private _logoutUrl: string = "http://localhost:8080/olx-seller-1/user/logout";
  private _checkSession: string = "http://localhost:8080/olx-seller-1/user/check";
  msg: any;

  constructor(private _http: Http) {
  }

  submitLoginData(loginData) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.post(this._loginUrl, loginData, {headers: headers, withCredentials: true})
      .map((res: Response) => this.userDetails = res.json());
  }

  getUserDetails() {
    console.log(this.userDetails);
    if (this.userDetails) {
      return Promise.resolve(this.userDetails);
    } else {
      return Promise.reject("Data not available");
    }
  }

  isValidUser() {
    if (this.userDetails!=undefined) {
      return true;
    }
    else {
      return false;
    }
  }

  logout() {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    console.log("Logout called in login service")
    this.userDetails=null
    return this._http.get(this._logoutUrl, {
      headers: headers,
      withCredentials: true
    }).map((res: Response) => res.json());
  }

  checkSession() {

    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this._http.get(this._checkSession, {headers: headers, withCredentials: true})
      .map((res: Response) =>res.json());


  }
}
