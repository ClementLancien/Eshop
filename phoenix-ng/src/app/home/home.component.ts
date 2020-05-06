import { Component, OnInit } from '@angular/core';
import { SessionService } from '../services/session.service';
import { CatalogService } from '../services/catalog.service';
import { Product } from '../domain/product';
import { ProductTag } from '../domain/product-tag';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  products: Product[];
  tags: ProductTag[];

  tagSelected: string= "Coup de coeur";

  constructor(
    private session: SessionService,
    private service: CatalogService
  ) { }

  ngOnInit() {
    this.service.searchProducts("", -1, this.tagSelected, false).subscribe(
      searchResult => this.products = searchResult.products,
      // products => console.log(this.search.products),
    );
    this.service.loadTags().subscribe(
      tags => this.tags = tags
    )
  }

  changeTag(tag){
    this.tagSelected=tag;
    this.service.searchProducts("", -1, this.tagSelected, false).subscribe(
      searchResult => this.products = searchResult.products,
      // products => console.log(this.search.products),
    );
  }

}
