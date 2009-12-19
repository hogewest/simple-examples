package demo.smart.service;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;

import demo.smart.dao.AccountDao;
import demo.smart.dao.ProfileDao;
import demo.smart.dao.SignonDao;
import demo.smart.entity.Account;
import demo.smart.entity.Profile;
import demo.smart.entity.Signon;

public class AccountService {

    @Resource
    protected AccountDao accountDao;

    @Resource
    protected ProfileDao profileDao;

    @Resource
    protected SignonDao signonDao;

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        return accountDao.getAccountByUsernameAndPassword(username, password);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);

        Profile profile = new Profile();
        Beans.copy(account, profile).execute();
        profileDao.insertProfile(profile);

        Signon signon = new Signon();
        Beans.copy(account, signon).execute();
        signonDao.insertSignon(signon);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

        Profile profile = new Profile();
        Beans.copy(account, profile).execute();
        profileDao.updateProfile(profile);

        Signon signon = new Signon();
        Beans.copy(account, signon).execute();
        if (signon.password != null && signon.password.length() > 0) {
            signonDao.updateSignon(signon);
        }
    }

}
