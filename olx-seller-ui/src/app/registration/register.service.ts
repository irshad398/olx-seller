import {Injectable} from "@angular/core";
import {Http,Response} from "@angular/http";
import {Headers} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class RegisterService{

  private _registerUrl:string="http://localhost:8080/olx-seller-1/user/register";
  constructor(private _http:Http){}
  submitUserData(userData)
  {
    var headers=new Headers();
    headers.append('Content-Type',
      'application/json');

    return this._http.post(this._registerUrl,userData)
      .map((res:Response)=>res.json());
  }

}
