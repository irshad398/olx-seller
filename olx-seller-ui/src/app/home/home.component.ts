import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Product} from "../products/product";
import {ProductService} from "../products/product.service";
import {LoginService} from "../login/login.service";

@Component({
  // selector:'seller-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],

})
export class HomeComponent implements OnInit {
  ngOnInit() {
    console.log('hai');
  }
}
