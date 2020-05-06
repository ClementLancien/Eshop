import { Line } from './line';
import { Basket } from './basket';

export class Order {

    customer: string;
    lines:Array<Line> = [];
    

    constructor(){}

    addCustomerToOrder(customer: string){
        //console.log(customer);
        this.customer = customer;
        
    }

    addBasketToOrder(basket: Basket) {
        basket.lines.forEach(line => {
            this.lines.push(new Line(Number(line.product.id), Number(line.quantity)))
        });
        //console.log("add basket  " + JSON.stringify(this.lines));
    }
}
