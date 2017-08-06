import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {NavigationComponent} from "./navigation/navigation.component";
import {LoginModule} from "./login/login.module";
import {AppRoutingModule} from "./app-routing.module";
import {RegisterModule} from "./registration/register.module";

@NgModule({
  declarations: [
    AppComponent,NavigationComponent
  ],
  imports: [
    BrowserModule,LoginModule,AppRoutingModule,RegisterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
