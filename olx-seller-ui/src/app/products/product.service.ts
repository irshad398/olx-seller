import {Injectable} from '@angular/core';

import {Product} from "./product";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Category} from "./category";
import {parseHttpResponse} from "selenium-webdriver/http";

@Injectable()
export class ProductService {
  private products: Product[];
  private myProducts: Product[];
  private categories:Category[];

  res: any;
  private _getProductsUrl: string = "http://localhost:8080/olx-seller-1/products";
  private _getRecentProductsUrl: string = "http://localhost:8080/olx-seller-1/products/recent";
  private _getProductsByCatUrl: string = "http://localhost:8080/olx-seller-1/products/cat";
  private _myProductsUrl: string = "http://localhost:8080/olx-seller-1/user/products";
  private _getCategoriesUrl:string="http://localhost:8080/olx-seller-1/products/categories";

  constructor(private _http: Http) {
  }

  getProducts(): Observable<Product[]> {
    return this._http.get(this._getProductsUrl)
      .map(response => this.products = (response.json()));

  }
  getRecentProducts(): Observable<Product[]>{
    return this._http.get(this._getRecentProductsUrl)
      .map(response => response.json());
  }
  getCategories():Observable<Category[]>{
    return this._http.get(this._getCategoriesUrl)
      .map(response=>this.categories=(response.json()))
  }
  getProductsByCat(catId): Observable<Product[]> {
    return this._http.get(this._getProductsByCatUrl+'/'+catId)
      .map(response => this.products = (response.json()));

  }
  getProductsOrderBy(orderById): Observable<Product[]> {
    return this._http.get(this._getProductsUrl+'/'+orderById)
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
