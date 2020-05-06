import { Injectable } from '@angular/core';
import { ProductsComponent } from '../products/products.component';
import { Product } from '../domain/product';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  products: Product[];
  
  constructor() { }
}
