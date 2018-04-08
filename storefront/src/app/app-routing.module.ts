import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { ListingComponent } from './pages/listing/listing.component';
import { CreateComponent } from './pages/create/create.component';
import { KitchensinkComponent } from './pages/kitchensink/kitchensink.component';
import { AdoptComponent } from './pages/adopt/adopt.component';
import { ViewComponent } from './pages/view/view.component';

const routes: Routes = [
  { path: '', component: ListingComponent },
  { path: 'adopt/:id', component: AdoptComponent },
  { path: 'view/:id', component: ViewComponent },
  { path: 'create', component: CreateComponent },
  { path: 'sink', component: KitchensinkComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
