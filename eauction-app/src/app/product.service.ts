import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
apiUrl:string = "http://seller-alb-1545964675.us-east-2.elb.amazonaws.com/seller/listProducts";
bidUrl:string = "http://seller-alb-1545964675.us-east-2.elb.amazonaws.com/seller/show-bids/";


  constructor(private http:HttpClient) { }

 public listAllProducts() {
      return this.http.get(this.apiUrl);
  }

  public listBids(productId) {
    return this.http.get(this.bidUrl+productId);
  }
}
