import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { CatalogService } from '../services/catalog.service';
import { Product } from '../domain/product';
import { SessionService } from '../services/session.service';
import { ToastService } from '../services/toast.service';
import { Review } from '../domain/review';
import { ReviewsService } from '../services/reviews.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  product: Product;
  reviews: Review[] = [];
  score: number = 0;
  data: any = {
    rating: 1,
    content: "Ce texte peut être édité par l'utilisateur."
  }

  constructor(
    private route: ActivatedRoute,
    private service: CatalogService,
    private session: SessionService,
    private reviewService: ReviewsService,
    private toastService: ToastService
  ) { }

  ngOnInit( ) {
    this.session.addToBasketSuccess = false;

    this.route.paramMap.subscribe((params: ParamMap) => {
      const id = this.route.snapshot.paramMap.get('id');
      console.log(`Détail du produit :  ${id}`);
      this.service.loadProduct(id)
                  .subscribe(
                    product => this.product = product
                  );
      this.reviewService.searchReview(id)
                        .subscribe(
                          reviews =>{
                            this.reviews = reviews
                            this.calculScore();
                          } 

                        )
      });
  }

  addReview() {

    if (this.session.user == null) {
      this.toastService.showError("Vous devez être connecté pour ajouter votre avis");
    } else {
      this.reviewService.createReview(this.product.id, this.session.user.username, this.data.rating, this.data.content)
          .subscribe(
            () => this.onSuccess(this.product.id),
            error => this.onError(error)
         );
      
    }
  }

  onError(error){

  }

  onSuccess(id) {
    this.reviewService.searchReview(id)
                        .subscribe(
                          reviews => {
                            this.reviews = reviews
                            this.calculScore()
                          }
                        );
    
    //this.reviews.push(new Review( this.session.user.username, this.product.id, this.data.content, this.data.rating));
  }


  changeRating(value){
    this.data.rating=value;
  }

  onContentChange(content){
    this.data.content=content;
  }

  calculScore() {
    //console.log("score")
    //console.log("nombre avis : " + this.reviews.length)
    // this.score=0;
    // this.reviews.forEach(review => {
    //   this.score += review.rating
    //   console.log(review.rating)
    // });
    if (this.reviews.length == 0 ){
      this.score = 0;
    } else {
      this.score =  Number(this.reviews.reduce(
        (accumulator, currentValue) => accumulator + currentValue.rating
        , 0)) / this.reviews.length
    }
    //console.log("score : " + this.score)
  }
}
