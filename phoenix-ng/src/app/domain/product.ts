export class Product {
    id: number;
    type: string;
    name: string;
    price: number;

    constructor(id: number, type: string, name: string, price: number) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }
}