package org.example;

import org.example.Util.JsonSerializerd;
import org.example.enums.AccountStatus;
import org.example.enums.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirecteur;
import org.example.repository.AccountRepository;
import org.example.repository.AccountRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws IOException {

        JsonSerializerd<BankAccount> bankAccountJsonSerializerd = new JsonSerializerd<>();
        AccountRepositoryImpl accountRepository =AccountRepositoryImpl.getInstance();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                accountRepository.populateData();
            }).start();
        }
        System.out.println("Tape a character");
        System.in.read();
        List<BankAccount> bankAccounts = accountRepository.searchAccount (new Predicate<BankAccount>() {
            @Override
            public boolean test (BankAccount bankAccount) {
                return bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT);
            }
        });
        //List<BankAccount> bankAccounts = accountRepository.findAll();
        bankAccounts.stream().
                map(acc -> bankAccountJsonSerializerd.toJson(acc))
                .forEach(System.out::println);
        System.out.println("= ==============");

        BankAccount account = accountRepository.findById(1L).orElse(null);
        if (account != null) {
            System.out.println(bankAccountJsonSerializerd.toJson(account));

      /*  BankDirecteur.accountBuilder().Build();
        BankAccount bankAccount=BankAccount.builder()
                .accountId(1L)
                .currency("MAD")
                .status(AccountStatus.CREATED)
                .type(AccountType.SAVING_ACCOUNT)
                .balance(7000)
                .Build();
        System.out.println(bankAccount.toString());*/
        }
    }
}
