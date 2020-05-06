import { Component, OnInit } from '@angular/core';
import { Product } from '../domain/product';
import { ProductFamily } from '../domain/product-family';
import { CatalogService } from '../services/catalog.service';
import { SearchService } from '../services/search.service';
import { debounceTime, distinctUntilChanged, map, switchMap, catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Suggest } from '../domain/suggest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  data: any = {
    keyword: '',
    familyId: -1
  }

  families:Array<ProductFamily>;
  products:Array<Product> = [];
  sizeCollection: number;
  //page: number;
  
  constructor(
    private service: CatalogService,
    private search: SearchService,
    private router: Router,
  ) {}
  
  ngOnInit() {
    //this.families = this.service.loadFamilies();
    //console.log(families);
    this.service.loadFamilies().subscribe(
      families => this.families = families
    );

    this.service.searchProducts("", -1).subscribe(
      searchResult => {
        this.search.products = searchResult.products;
        this.sizeCollection = searchResult.sizeCollection;
      }
    );
   
    console.log("sizeCollection : " + this.numberPages())
    //console.log("number of pages " + this.page);
  }

  numberPages() {
    return Number(this.sizeCollection/10);
  }

  onSearch(){
    if(typeof this.data.keyword !== 'string') return;

    console.log(`Chercher : ${this.data.keyword} / ${this.data.familyId}`);

    //this.products = this.service.searchProducts(keyword, familyId);
    this.service.searchProducts(this.data.keyword, this.data.familyId).subscribe(
      searchResult => {
        this.search.products = searchResult.products;
        this.sizeCollection = searchResult.sizeCollection;
      }
    );
    console.log(this.sizeCollection);
  }

  onProductSelected(p: Product){
    console.log(`Afficher le détail de : ${p.name}`);
  }


  searchSuggest = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // tap(() => this.searching = true),
      switchMap(term => term.length < 2 ? []
        : this.service.searchSuggest(term, this.data.familyId).pipe(
          // tap(() => this.searchFailed = false),
          catchError(() => {
            // this.searchFailed = true;
            return of([]);
          }))
      ),
      // tap(() => this.searching = false)
    )

    suggestResultFormatter = (sugg: Suggest):string => `${sugg.name} - ${sugg.name}`;
    suggestInputFormatter = (sugg: Suggest):string => sugg.name;

    onSuggestSelected(event) {
      const sugg = event.item;
      console.log('Suggestion sélectionné', sugg);
      this.router.navigate(['detail', sugg.id]);
    }
  }