import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Order } from '../domain/order';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(
    private http: HttpClient,
    private session: SessionService
  ) { }

  loadOrders(): Observable<any>{
    return this.http.get<any>(`/api/orders?cust=${this.session.user.username}`);
  }

  placeOrder(order: Order): Observable<void> {
    return this.http.post<void>(`/api/orders/place`, order);
  }

}
