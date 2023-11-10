package org.example.model;

public class BankDirecteur {
    public static BankAccount.AccountBuilder accountBuilder(){
        return  new BankAccount.AccountBuilder();
    }
}
