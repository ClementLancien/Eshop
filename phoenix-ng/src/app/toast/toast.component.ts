// toast.component.ts
import {Component, TemplateRef} from '@angular/core';
import { ToastService } from '../services/toast.service';

@Component({
  selector: 'app-toasts',
  templateUrl: './toast.component.html',
  host: {'[class.ngb-toasts]': 'true'},
  styleUrls: ['./toast.component.css']
})
export class ToastComponent {
  constructor(public toastService: ToastService) {}

  isTemplate(toast) { return toast.textOrTpl instanceof TemplateRef; }
}
