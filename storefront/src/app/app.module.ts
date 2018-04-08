import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from "@angular/flex-layout";

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { PetCardComponent } from './components/pet-card/pet-card.component';
import { KitchensinkComponent } from './pages/kitchensink/kitchensink.component';
import { MaterialModule } from './util/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PetService } from './services/pet.service';
import { HttpClientModule } from '@angular/common/http';
import { ListingComponent } from './pages/listing/listing.component';
import { CreateComponent } from './pages/create/create.component';
import { ViewComponent } from './pages/view/view.component';
import { AdoptComponent } from './pages/adopt/adopt.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PetCardComponent,
    KitchensinkComponent,
    ListingComponent,
    CreateComponent,
    ViewComponent,
    AdoptComponent
  ],
  imports: [
    BrowserModule,
    FlexLayoutModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  providers: [PetService],
  bootstrap: [AppComponent]
})
export class AppModule { }
