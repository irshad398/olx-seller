import {Component} from '@angular/core';

import {Product} from "./product";
import {ProductService} from "./product.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Location} from '@angular/common';

@Component({
  selector: 'myproduct-detail',
  templateUrl: './myproduct-detail.component.html',
  styleUrls: ['./myproduct-detail.component.css']
})
export class MyProductDetailComponent {
  myProduct: Product;
  myProduct_id: number;

  constructor(private _productService: ProductService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: ParamMap) => {
      this.myProduct_id = params['id']
    });

    this._productService.getProduct(this.myProduct_id)
      .subscribe(data => {
        console.log(data);
        this.myProduct = data;
      });
  }

  updateProduct(myProduct) {
    this._productService.updateProduct(myProduct)
      .subscribe(data => {
        alert("updated! ");
      });
  }

  goBack(): void {
    this.location.back();
  }

}
