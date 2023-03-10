import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoButtonDeleteComponent } from './todo-button-delete.component';

describe('TodoButtonDeleteComponent', () => {
  let component: TodoButtonDeleteComponent;
  let fixture: ComponentFixture<TodoButtonDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoButtonDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodoButtonDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
