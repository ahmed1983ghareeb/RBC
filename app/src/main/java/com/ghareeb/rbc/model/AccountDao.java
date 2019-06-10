package com.ghareeb.rbc.model;

import java.util.Collection;
import java.util.LinkedList;

public class AccountDao {

    LinkedList<Account> accounts = new LinkedList<>();

    public boolean addAccount(Account account){
        if(accounts.contains(account)){
            return false;
        }else{
            accounts.add(account);
            return true;
        }
    }

    public boolean removeAccount(Account account){
        if(!accounts.contains(account)){
            return false;
        }else{
            accounts.remove(account);
            return true;
        }
    }

    public boolean updateAccount(Account account, Account newAccount){

        if(!accounts.contains(account)){
            return false;
        }else{
            accounts.remove(account);
            accounts.add(newAccount);
            return true;
        }
    }

    public Account getAccountByEmail(String email){
        for(Account account : accounts){
            if (account.getEmail().equals(email))
                return account;
        }

        return null;
    }

    public Account getAccountById(int id){
        //make sure we don't get index outOfBound error
        if (accounts.size() - 1 >= id) {
            return accounts.get(id);
        }else{
            return null;
        }
    }
    public Collection<Account> getAll(){
        return accounts;
    }

}
