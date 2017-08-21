import {Component} from '@angular/core';

import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Location} from '@angular/common';
import {LoginService} from "../../login/login.service";


@Component({
  selector: 'product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent {
  product: Product;
  product_id: number;
  username:string;
  msg:boolean;
  constructor(private _productService: ProductService,
              private route: ActivatedRoute,
              private location: Location, private loginService:LoginService,private router:Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: ParamMap) => {
      this.product_id = params['id']
      this.username=window.localStorage.getItem('user');
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
  logout() {

    this.loginService.logout().subscribe(data => {
      this.msg = data
    }, err => {
      console.log("Error", err)
    });
    if (this.msg == true) {
      console.log("Logged out!");
      window.localStorage.clear();
    }
    this.router.navigate(['/login'])
  }

}
