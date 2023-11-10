package org.example;

import org.example.enums.AccountStatus;
import org.example.enums.AccountType;
import org.example.model.BankAccount;
import org.example.model.Customer;

public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1=new BankAccount();
        account1.setAccountId(1L);
        account1.setCurrency("MAD");
        account1.setType(AccountType.CURRENT_ACCOUNT);
        account1.setBalance(900000);
        account1.setStatus(AccountStatus.ACTIVATED);
        account1.setCustomer(new Customer(1L, "safa"));



         BankAccount account3=account1.clone();

        System.out.println(account1);

        System.out.println(account3);

        System.out.println("******************************");

        account1.setBalance(30000);
        System.out.println(account1.getBalance());
        System.out.println(account3.getBalance());

        account1.getCustomer().setName("safaa azzouz ");
        System.out.println(account1);

        System.out.println(account3);
    }
}
