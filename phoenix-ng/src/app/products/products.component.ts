import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Product } from '../domain/product';
import { SearchService } from '../services/search.service';
import { SessionService } from '../services/session.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

 // @Input() products: Product[];
  @Output() productSelected: EventEmitter<Product>  = new EventEmitter();
  products: Product[];

  constructor(
    private search: SearchService,
    private session: SessionService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    console.log("Création ProductsComponent");
    //this.route.queryParams.subscribe(x => this.loadPage(x.page || 1))
  }


  onSelected(p: Product){
    console.log(`Produit selectionné : ${p.name}`);
    this.productSelected.emit(p);
  }

}
