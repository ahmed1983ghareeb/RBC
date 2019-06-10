package com.ghareeb.rbc.model;

public class AccountFactory {
   private static AccountDao accountDao = new AccountDao();

   public static AccountDao getAccountDao(){
       return accountDao;
   }

}
