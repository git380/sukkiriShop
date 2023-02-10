package model;

import dao.AccountDAO;

public class LoginLogic {
    public boolean execute(Login login) {
        AccountDAO dao = new AccountDAO();
        Account account = dao.findByLogin(login);
        return account != null;
    }
    public void newAccount(Account account) {
        AccountDAO dao = new AccountDAO();
        dao.insertAccount(account);
    }
    public void delAccount(String userId) {
        AccountDAO dao = new AccountDAO();
        dao.deleteUser(userId);
    }
}