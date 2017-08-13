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
  private p: number = 1;
  searchInput={
    title:'',
    catId:''
  };
  returnedProducts:Product[];
  selectedProduct:Product;
  constructor(private _productSearchService: ProductSearchService,
              private router: Router) {
  }

  searchProduct(searchData){
    console.log(searchData)
    this._productSearchService.searchProduct(searchData)
      .subscribe(data=>{
        this.returnedProducts=data;
      },()=>{ console.log("error in searching data")
      });

  }
  selectProduct(product:Product){
    this.selectedProduct=product;
  }
  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/'+ selectedProductId,{id:selectedProductId}]);
  }

  ngOnInit(): void {

  }

}
