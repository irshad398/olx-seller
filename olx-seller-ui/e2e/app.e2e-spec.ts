import { OlxSellerUiPage } from './app.po';

describe('olx-seller-ui App', () => {
  let page: OlxSellerUiPage;

  beforeEach(() => {
    page = new OlxSellerUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
