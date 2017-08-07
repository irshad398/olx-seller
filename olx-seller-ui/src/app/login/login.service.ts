import {Injectable} from "@angular/core";
import {Http,Response} from "@angular/http";
import {Headers} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class LoginService{

  private _loginUrl:string="http://localhost:8080/olx-seller-1/user/login";
  constructor(private _http:Http){}
  submitLoginData(loginData)
  {
    var headers=new Headers();
    headers.append('Content-Type',
      'application/json');

    return this._http.post(this._loginUrl,loginData)
      .map((res:Response)=>res.json());
  }

}
