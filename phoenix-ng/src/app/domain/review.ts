export class Review {
    username: string;
    productId: number;
    comment: string;
    rating: number;

    constructor(username: string, productId: number, comment: string, rating:number){
        this.username=username;
        this.productId=productId;
        this.comment=comment;
        this.rating=rating;
    }
}
