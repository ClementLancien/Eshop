import { Component, OnInit } from '@angular/core';
import { OrdersService } from '../services/orders.service';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.css']
})
export class MyOrderComponent implements OnInit {

  orders: Array<any>;

  constructor(
    private service: OrdersService,
    private session: SessionService
  ) { }

  ngOnInit() {
    this.service.loadOrders().subscribe(
      orders => this.orders = orders
    );
  }

}
