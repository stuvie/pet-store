import { CreatePage } from './create.po';

describe('storefront App create page', () => {
  let page: CreatePage;

  beforeEach(() => {
    page = new CreatePage();
  });

  it('should display correct welcome heading', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('New Pet Input Form');
  });

  it('should display correct footer', () => {
    page.navigateTo();
    expect(page.getFooterText()).toEqual('Pet Shop Boys, 2018');
  });
});
