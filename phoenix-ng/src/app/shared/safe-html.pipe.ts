import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'safeHtml'
})
export class SafeHtmlPipe implements PipeTransform {

  constructor(
    private Sanitizer: DomSanitizer
  ){}

  transform(value: any, ...args: any[]): any {
    console.log(this.Sanitizer.bypassSecurityTrustHtml(value));
    return this.Sanitizer.bypassSecurityTrustHtml(value);

  }

}
