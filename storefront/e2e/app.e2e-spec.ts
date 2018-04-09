import { AppPage } from './app.po';

describe('storefront App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('homepage should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to Pet Shop Boys!');
  });
});
