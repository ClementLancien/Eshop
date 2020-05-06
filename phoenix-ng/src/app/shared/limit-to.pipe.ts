//ng g pipe shared/limit-to

import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'limitTo'
})
export class LimitToPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {

    let size: number = 200;
    if (args && args.length > 0) {
      size = parseInt(args[0]);
    }

    let str: String = value;
    if (str && str.length > size) {
      str = str.slice(0, size - 1) + "\u2026";
    }
    return str;
  }

}
