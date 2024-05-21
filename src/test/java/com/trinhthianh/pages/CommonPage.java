package com.trinhthianh.pages;

public class CommonPage {
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    public MyProjectsPage myProjectsPage;
    public InboxPage inboxPage;
    public SearchPage searchPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }
    public SignUpPage signUpPage() {
        if (signUpPage == null) {
            signUpPage = new SignUpPage();
        }
        return signUpPage;
    }

    public MyProjectsPage getMyProjectsPage() {
        if (myProjectsPage == null) {
            myProjectsPage = new MyProjectsPage();
        }
        return myProjectsPage;
    }
    public InboxPage inboxPage() {
        if (inboxPage == null) {
            inboxPage = new InboxPage();
        }
        return inboxPage;
    }

    public SearchPage searchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

}
