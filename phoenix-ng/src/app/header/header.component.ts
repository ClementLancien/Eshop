import { Component, OnInit, OnChanges, SimpleChange, SimpleChanges, Input } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';

/*
le header n'est pas dans le router-outlet donc on recharge a chaque fois l'application
*/

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private session: SessionService,
  ) { }

  ngOnInit() {
  }

  logOut(){
    console.log('here');
    this.session.user = null;
    
  }

}
