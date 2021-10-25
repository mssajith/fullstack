import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
selectedItem: string;
products=[];
bids=[];
selProductId:string;

productName:string;
shortDescription:string;
detailDescription:string;
startingPrice:string;
bidEndDate:string;
category:string;


  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productName =  "";
    this.shortDescription = "";
    this.detailDescription = "";
    this.category = "";
    this.startingPrice = "";
    this.bidEndDate = "";
  }

  public populateAllProducts() {
    this.productService.listAllProducts().subscribe(
      (data: any[]) => {
        console.log("Data " + data);
        this.products = data;
      }
    );
  }

public populateBids() {
  console.log("Selected = " + this.selectedItem)
  this.productService.listBids(this.selectedItem).subscribe(
    (data: any[]) => {
      console.log("Bids ::"+ data);
      this.bids=data;
    }
  );
}

populateProductDesc(ob) {
  console.log("Selected Values are " + ob.target.value);
  for (var _i = 0; _i < this.products.length; _i++) {
    var id = this.products[_i].id;

    if (id === ob.target.value) {
      this.productName =  this.products[_i].productName;
      this.shortDescription = this.products[_i].shortDescription;
      this.detailDescription = this.products[_i].detailDescription;
      this.category = this.products[_i].category;
      this.startingPrice = this.products[_i].startingPrice;
      this.bidEndDate = this.products[_i].bidEndDate;      
      
    }

       
  }
}

}
