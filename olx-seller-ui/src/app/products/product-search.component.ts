import {ProductSearchService} from "./product-search.service";
import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Product} from "./product";
import {Subject} from "rxjs/Subject";
import {Router} from "@angular/router";

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css'],
})
export class ProductSearchComponent implements OnInit {

  products: Observable<Product[]>;
  private searchTerms = new Subject<string>();

  constructor(private _productSearchService: ProductSearchService,
              private router: Router) {
  }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.products = this.searchTerms
      .debounceTime(300)        // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time the term changes
        // return the http search observable
        ? this._productSearchService.search(term)
        // or the observable of empty heroes if there was no search term
        : Observable.of<Product[]>([]))
      .catch(error => {
        // TODO: add real error handling
        console.log(error);
        return Observable.of<Product[]>([]);
      });
  }

}
