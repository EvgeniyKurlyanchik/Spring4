import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceComponent} from "../product-service/product-service.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  product = new Product(null, "", 1, "");
  error = false;
  errorMessage = "";

  constructor(private restResourse: ProductServiceComponent,
              private route: ActivatedRoute,
              private router: Router) {
  }


  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.restResourse.findById(param['id'])
        .subscribe(res => {
          this.product = res;
          this.error = false;
          this.errorMessage = "";
        }, error => {
          console.log(error);
          this.error = true;
          this.errorMessage = error.error;
        })
    })
  }
  save() {
    this.restResourse.save(this.product)
      .subscribe(res => {
        console.log(res)
        this.router.navigateByUrl('/product')
        this.error = false;
        this.errorMessage = "";
      }, err => {
        console.log(err);
        this.error = true;
        this.errorMessage = err.error;
      })
  }

}
