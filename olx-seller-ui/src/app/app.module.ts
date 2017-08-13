import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';

import {LoginModule} from "./login/login.module";
import {AppRoutingModule} from "./app-routing.module";
import {RegisterModule} from "./registration/register.module";
import {HomeModule} from "./home/home.module";
import {AuthGuard} from "./authguard";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,LoginModule,AppRoutingModule,RegisterModule, HomeModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
  exports:[]
})
export class AppModule { }
