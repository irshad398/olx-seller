import {Component, OnInit} from "@angular/core";
import {Product} from "./product";
import {ProductService} from "./product.service";
import {Router} from "@angular/router";
import {Location} from '@angular/common';

@Component({
  selector: 'my-products',
  templateUrl: './my-products.component.html',
  styleUrls: ['./my-products.component.css'],
})
export class MyProductsComponent implements OnInit {
  myProducts: Product[];
  private p: number = 1;
  order: string = "title";
  newProduct = {
    title: '',
    category_id: '',
    price: '',
    model: '',
    old_or_new: '',
    description: '',
  };

  constructor(private _productService: ProductService, private _location: Location, private router: Router) {
  }

  ngOnInit() {
    this._productService.getMyProducts()
      .subscribe(data => {
        console.log(data);
        this.myProducts = data
      });
  }

  gotoDetail(selectedProductId) {

    console.log(selectedProductId);
    this.router.navigate(['products/detail/' + selectedProductId, {id: selectedProductId}]);
  }

  gotoMyProductDetail(selectedProductId) {
    console.log(selectedProductId);
    this.router.navigate(['my-products/detail/' + selectedProductId, {id: selectedProductId}]);
  }

  addProduct(newProduct) {
    console.log(newProduct);
    this._productService.addProduct(newProduct)
      .subscribe(data => {
        if (data.json() == true) {
          alert("Added product!");
        }
        else {
          alert("Unable to add the product!");
        }
        // this._location.back();
      }, err => {
        console.log("Unable to add the product: ")
      });
  }

  deleteMyProduct(myProductId) {
    console.log(myProductId);
    this._productService.deleteMyProduct(myProductId)
      .subscribe(
        data => {
          if (data.json() == true) {
            alert("Deleted!");
            // this._location.back();
            // this.router.navigate(['/home/my-products'])
          }
          else {
            console.log("Unable to delete")
          }

        }, err => {
          console.log("error in deleting");
        }
      );
  }
}
