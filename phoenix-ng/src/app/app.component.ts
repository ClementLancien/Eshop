import { Component } from '@angular/core';
import { Product } from './domain/product';
import { ToastService } from './services/toast.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  app = { title : 'eshop'};

  constructor(
    public toastService: ToastService
  ){

  };

}
