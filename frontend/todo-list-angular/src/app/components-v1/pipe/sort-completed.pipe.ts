import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'sortCompleted' })
export class SortCompletedPipe implements PipeTransform {
  transform(array: any[]): any[] {
    if (!Array.isArray(array)) {
      return array;
    }

    const completed = array.filter(item => item.completed);
    const notCompleted = array.filter(item => !item.completed);
    return [...notCompleted, ...completed];
  }
}
