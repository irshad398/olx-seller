import {Component} from '@angular/core';

import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Location} from '@angular/common';
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'myproduct-detail',
  templateUrl: './myproduct-detail.component.html',
  styleUrls: ['./myproduct-detail.component.css']
})
export class MyProductDetailComponent {
  myProduct: Product;
  myProduct_id: number;
  msg:boolean;
  username:string;

  constructor(private _productService: ProductService,
              private route: ActivatedRoute,
              private location: Location,private loginService:LoginService,
              private router:Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: ParamMap) => {
      this.myProduct_id = params['id']
      this.loginService.username.subscribe(data=>this.username=data);

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
        this.goBack();
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
      console.log("Logged out!")
    }
    this.router.navigate(['/login'])
  }

}
