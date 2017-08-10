import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import {Product} from "./product";

@Injectable()
export class ProductSearchService {
  private products: Product[];
  private _searchProductsUrl:string="http://localhost:8080/olx-seller-1/products/search";
  constructor(private _http: Http) {}

  searchProduct(searchData): Observable<Product[]> {

    return this._http.post(this._searchProductsUrl,searchData)
      .map(response => this.products=(response.json()));
  }
}

