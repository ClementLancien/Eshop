import { Component, OnInit } from '@angular/core';
import { SessionService } from '../services/session.service';
import { OrdersService } from '../services/orders.service';
import { Order } from '../domain/order';
import { Basket } from '../domain/basket';
import { Router } from '@angular/router';
import { ToastService } from '../services/toast.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  placeOrderSuccess: boolean;
  placeOrderFailed: boolean;
  constructor(
    private session: SessionService,
    private service: OrdersService,
    private route: Router,
    private toastService: ToastService

  ) { }

  ngOnInit() {
    this.placeOrderSuccess = false;
    this.placeOrderFailed = false;
  }

  placeOrder(){
    if (this.session.user != null) {
      const order = new Order();
      order.addCustomerToOrder(this.session.user.username);
      order.addBasketToOrder(this.session.basket);
      //console.log(JSON.stringify(order));
      this.placeOrderSuccess = false;
      this.placeOrderFailed = false;
      this.service.placeOrder(order)
                  .subscribe(
                    () => this.onSuccess(),
                    error => this.onError(error)
                  );
    } else {
      this.route.navigate(['login']);
      this.toastService.showError("Vous devez être connecté pour passer une commande!!!");
      //this.session.basket.clean();
      this.session.cleanBasket();
    }
  }


  onSuccess(){
    //console.log("Commande ajouté");
    this.placeOrderSuccess = true;
    this.session.cleanBasket();
    //this.session.basketQuantity = 0;
    this.toastService.showSuccess("Votre commande a bien été enregistré!");
  }

  onError(error){
    this.placeOrderFailed = true;
    //console.log("Echec de la commande");
  }

  onChangeQuantity(value: number, id:number){
    console.log("quantité modifié : " + value + " id produit : " + id);
  }

  

}
