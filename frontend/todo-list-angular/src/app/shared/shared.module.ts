import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from './header/header.component';
import {MaterialModule} from "../material/material.module";
import {ConfirmationDialogComponent} from "./confirmation-dialog/confirmation-dialog.component";
import {ErrorDialogComponent} from "./error-dialog/error-dialog.component";

@NgModule({
  declarations: [
    HeaderComponent,
    ConfirmationDialogComponent,
    ErrorDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
  ],
  exports: [
    HeaderComponent,
    ConfirmationDialogComponent,
    ErrorDialogComponent
  ]
})
export class SharedModule {
}
