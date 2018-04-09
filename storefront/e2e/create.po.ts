import { browser, by, element } from 'protractor';

export class CreatePage {
  navigateTo() {
    return browser.get('/create');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  getFooterText() {
    return element(by.css('.footer span')).getText();
  }
}
