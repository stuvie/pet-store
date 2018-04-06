import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { ListingComponent } from './pages/listing/listing.component';
import { CreateComponent } from './pages/create/create.component';
import { KitchensinkComponent } from './pages/kitchensink/kitchensink.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'list', component: ListingComponent },
  { path: 'create', component: CreateComponent },
  { path: 'sink', component: KitchensinkComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
