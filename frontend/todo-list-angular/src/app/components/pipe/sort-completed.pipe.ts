import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'sortCompleted'
})
export class SortCompletedPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
