import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../domain/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {

  constructor(
    private http: HttpClient
  ) { }

  searchReview(productId): Observable<Review[]>{
    return this.http.get<Review[]>(`/api/reviews/${productId}`);
  }

  createReview(productId: number,username: string, rating: number, comment: string): Observable<void> {
    return this.http.put<void>('/api/reviews', {username, productId, rating, comment});
  }
}
