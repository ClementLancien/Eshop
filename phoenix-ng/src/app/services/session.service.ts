import { Injectable } from '@angular/core';
import { User } from '../domain/user';
import { Basket } from '../domain/basket';
import { Product } from '../domain/product';
import { ToastService } from './toast.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  user: User;

  basket: Basket = new Basket();
  basketQuantity: number = 0; 

  addToBasketSuccess: boolean;

  constructor(
    private session: SessionService,
    public toastService: ToastService
  ) { }

  addToBasket(quantity: number, product: Product){
    //console.log("taille panier " +  this.session.basket.lines.length);
    this.addToBasketSuccess = true;
    this.basket.addLine(Number(quantity), product);
    this.basketQuantity += Number(quantity);
    this.toastService.showSuccess("Article ajouté avec succès à votre panier.");
   
  }

  onProductModifyQuantity(productId:number, quantity:number) {
    this.basket.modifyProductQuantity(productId, quantity);
    this.calculBasketQuantity();
  }

  calculTotalAmount(): number {
    let totalAmount: number = 0;
    this.basket.lines.forEach(line => {
      totalAmount += line.quantity * line.product.price
    });
    return totalAmount;
  }

  onDeleteOrderLine(productId: number) {
    this.basket.deleteOrderLine(productId);
    this.calculBasketQuantity();
  }

  calculBasketQuantity() {
   this.basketQuantity = this.basket.calculQuantity();
  }

  cleanBasket() {
    this.basket = new Basket();
    this.calculBasketQuantity();
  }


  // showStandard() {
  //   this.toastService.show('I am a standard toast', {
  //     delay: 2000,
  //     autohide: true
  //   });
  // }
 
  // showSuccess(message: string) {
  //   this.toastService.show(message, {
  //     classname: 'bg-success text-light',
  //     delay: 5000 ,
  //     autohide: true,
  //     headertext: ' '
  //   });
  // }

  // showError() {
  //   this.toastService.show('I am a success toast', {
  //     classname: 'bg-danger text-light',
  //     delay: 2000 ,
  //     autohide: true,
  //     headertext: 'Error!!!'
  //   });
  // }
 
  // showCustomToast(customTpl) {
  //   this.toastService.show(customTpl, {
  //     classname: 'bg-info text-light',
  //     delay: 3000,
  //     autohide: true
  //   });
  // }



}
