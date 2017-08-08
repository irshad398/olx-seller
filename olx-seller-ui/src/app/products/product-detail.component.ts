import {Component} from '@angular/core';

import {Product} from "./product";
import {ProductService} from "./product.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Location} from '@angular/common';


@Component({
  selector: 'product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent {
  product: Product;
  product_id: number;

  constructor(private _productService: ProductService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: ParamMap) => {
      this.product_id = params['id']
    });

    this._productService.getProduct(this.product_id)
      .subscribe(data => {
        console.log(data);
        this.product = data;
      });
  }

  goBack(): void {
    this.location.back();
  }

}
