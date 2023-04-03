import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { GetagameComponent } from './components/getagame.component';
import { GameService } from './game.service';
import { DisplayagameComponent } from './components/displayagame.component';

@NgModule({
  declarations: [AppComponent, GetagameComponent, DisplayagameComponent],
  imports: [BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule],
  providers: [GameService],
  bootstrap: [AppComponent],
})
export class AppModule {}
