import { Injectable, OnInit } from '@angular/core';
import { ProductFamily } from '../domain/product-family';
import { Product } from '../domain/product';
import { Observable, of } from 'rxjs';
import { delay } from "rxjs/operators";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Suggest } from '../domain/suggest';
import { SearchResult } from '../domain/search-result';
import { ProductTag } from '../domain/product-tag';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  constructor(
    private http: HttpClient
  ) { }

  loadFamilies(): Observable<ProductFamily[]> {
    // return of( [
    //   new ProductFamily(1, 'Roman'),
    //   new ProductFamily(2, 'Fiction'),
    //   new ProductFamily(3, 'Histoire'),
    // ]);
    return this.http.get<ProductFamily[]>('/api/families');
  }

  searchProducts(keyword: string, familyId: number, tag?, randomize?: boolean ): Observable<SearchResult> {
    
    // const options = keyword ?
    //   { params: new HttpParams().set('q', keyword).set('id', familyId) } : {};

    return this.http.post<SearchResult>(`/api/search`, {keyword, familyId, "tag":tag||"", "randomize": randomize || false});
    // return of([
    //   new Product(1, "Livre", 'Stupeur et tremblements', 5.32),
    //   new Product(2, "Livre", 'Le voyage d\'hiver', 4.85),
    //   new Product(3, "Livre", 'Il était une fois l\'amour', 7.22),
    //   new Product(4, "Livre", 'Amerigo, récit d\'une erreur historique', 4.85),
    //   new Product(5, "Livre", 'Les adieux à la reine', 6.37),
    //   new Product(6, "Livre", 'Le roman de Raspoutine', 19.19),
    //   new Product(7, "Livre", 'Quand reviendras-tu ?', 7.22),
    //   new Product(8, "Livre", 'La note noire', 276.00),
    // ]).pipe(
    //   delay(500)
    // );
  }
  
  loadProduct(id): Observable<Product> {
    return this.http.get<Product>(`/api/products/${id}`);
  }

  searchSuggest(keyword, familyId): Observable<Suggest[]>{
    keyword = keyword.trim();
    const options = keyword ?
      { params: new HttpParams().set('q', keyword).set('id', familyId) } : {};
    return this.http.get<Suggest[]>('/api/suggest', options);
  }

  loadTags(): Observable<ProductTag[]>{
    return this.http.get<ProductTag[]>('/api/tags')
  }

  
}