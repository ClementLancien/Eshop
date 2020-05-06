import { Product } from './product';
import { OrderLine } from './order-line';

export class Basket {
    lines: Array<OrderLine> = [];
    
    addLine(quantity: number, product: Product){
        let isNotFound = true;
        this.lines.forEach(line => {
            if(line.product.id == product.id){
                line.quantity += Number(quantity);
                isNotFound = false;
                //console.log("Quantité :" + line.quantity);
                return;
            }
        });
        
        
        if(isNotFound){
            this.lines.push(new OrderLine(product, quantity));
        }
    }
    
    modifyProductQuantity(productId:number, quantity:number) {
        this.lines.forEach(line => {
            if(line.product.id == productId) {
              line.quantity = quantity;
              return;
            }
        });
      }
    
    deleteOrderLine(productId: number) {
        this.lines = this.lines.filter(line => line.product.id !== productId);
    }

    calculQuantity():number {
        return Number(this.lines.reduce(
                (accumulator, currentValue) => accumulator + currentValue.quantity
            , 0))
    }
}