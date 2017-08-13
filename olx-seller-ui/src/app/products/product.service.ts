import {Injectable} from '@angular/core';

import {Product} from "./product";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ProductService {
  private products: Product[];
  private myProducts: Product[];

  res: any;
  private _getProductsUrl: string = "http://localhost:8080/olx-seller-1/products";
  private _myProductsUrl: string = "http://localhost:8080/olx-seller-1/user/products";

  constructor(private _http: Http) {
  }

  getProducts(): Observable<Product[]> {
    return this._http.get(this._getProductsUrl)
      .map(response => this.products = (response.json()));

  }

  getProduct(id: number): Observable<Product> {
    console.log("product service: ", id);
    const url = `${this._getProductsUrl}/${id}`;
    return this._http.get(url)
      .map(response => response.json()
        /*console.log("Response:",this.res)*/
      );
  }

  getMyProducts(): Observable<Product[]> {
    return this._http.get(this._myProductsUrl, {withCredentials: true})
      .map(response => this.myProducts = (response.json()));
  }

  updateProduct(myProduct) {
    return this._http.put(this._myProductsUrl, myProduct, {withCredentials: true})
      .map(response => (response.toString()));
  }

  addProduct(newProduct) {
    return this._http.post(this._myProductsUrl, newProduct, {withCredentials: true})
  }

  deleteMyProduct(myProductId) {
    console.log("I am in product service..going to delete: " + myProductId)
    return this._http.delete(this._myProductsUrl + '/' + myProductId, {withCredentials: true});
  }

  goBack() {

  }

}
