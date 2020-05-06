import { Component, OnInit } from '@angular/core';
import { User } from '../domain/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { SessionService } from '../services/session.service';
import { ToastService } from '../services/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authFailed: boolean;

  constructor(
    private service: UserService,
    private router: Router,
    private session: SessionService,
    private toastService: ToastService
  ) { }

  ngOnInit() {
  }

  authenticate(form){
    if (form.invalid) return;
    
    const login = form.value
    //console.log(`Demande authentification : ${login.username} / ${login.password}`)
    this.authFailed = false;
    this.service.authenticate(login.username, login.password)
      .subscribe(
        user => this.onSuccess(user),
        error => this.onError(error)
      );
  }

  private onSuccess(user: User){
    //console.log(`Authentification reussi : ${user.firstName}  ${user.lastName}`);
    this.router.navigate(['/home']);
    this.session.user = user;
    this.toastService.showSuccess("Vous êtes maintenant connecté")
  }

  private onError(error: any){
    this.authFailed = true;
    //console.log("Error");
    this.toastService.showError("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect");
  }
}
