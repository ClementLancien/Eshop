import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { ToastService } from '../services/toast.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerFailed: boolean;
  registerSuccess: boolean;

  constructor(
    private service: UserService,
    private router: Router,
    private toastService: ToastService
  ) { }

  ngOnInit() {
    this.router.events.forEach((event) => {
      console.log(event);
    });
  }

  register(form){
    if(form.invalid) return;
    const register = form.value;
    this.registerFailed = false;
    this.registerSuccess = false;
    this.service.register(register.username, register.password, register.firstName, register.lastName)
      .subscribe(
        () => this.onSuccess(),
        error => this.onError(error));
  }

  onSuccess(){
    console.log("le client est inscrit");
    this.registerSuccess = true;
    this.toastService.showSuccess("Vous êtes maintenant inscrit");
    this.toastService.showWarning("Vous allez être rediriger dans 5 secondes")
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 5000);
   
  }
  
  onError(error){
    this.registerFailed = true;
    //console.log("Echec de l'inscription")
    this.toastService.showError("Echec de l'inscription : utilisateur existant");
  }

}
