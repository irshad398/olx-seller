import {Injectable} from '@angular/core';

import {Product} from "./product";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ProductService {
  private products: Product[];
  res:any;
  private _getProductsUrl:string="http://localhost:8080/olx-seller-1/products";
  constructor(private _http:Http){ }
  getProducts():Observable<Product[]> {
     return this._http.get(this._getProductsUrl)
       .map(response => this.products=(response.json()));

  }
  getProduct(id: number): Observable<Product> {
    console.log("product service: ",id);
    const url = `${this._getProductsUrl}/${id}`;
    return this._http.get(url)
      .map(response => response.json()
          /*console.log("Response:",this.res)*/
      );
  }
  goBack(){

  }
}
