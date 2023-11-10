package org.example.repository;

import org.example.enums.AccountStatus;
import org.example.enums.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirecteur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    private static final AccountRepositoryImpl accountRepository; static {
        System.out.println("Singleton instantiation");
        accountRepository=new AccountRepositoryImpl();
    }

    private AccountRepositoryImpl(){

    }
    private Map<Long,BankAccount> bankAccountMap=new HashMap<>();
    private  long accountsCount=0;
    @Override
    public synchronized BankAccount save(BankAccount bankAccount) {
        Long accountId =++accountsCount;
        bankAccount.setAccountId(accountId);
        bankAccountMap.put(accountId,bankAccount);
        return bankAccount ;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountMap.values().stream().toList();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        BankAccount account=bankAccountMap.get(id);
        if(account==null)
            return Optional.empty();
        else
            return  Optional.of(account);
    }

    @Override
    public List<BankAccount> searchAccount(Predicate<BankAccount> predicate) {
        return bankAccountMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount account) {
       bankAccountMap.put(account.getAccountId(), account);
       return  account;
    }

    @Override
    public void deleteBy(Long id) {
      bankAccountMap.remove(id);
    }
    public void populateData(){
        for(int i=0;i<10;i++){
           BankAccount  account = BankDirecteur.accountBuilder()
                   .balance(1000+Math.random()*90000)
                   .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                   .status(Math.random()>0.5? AccountStatus.CREATED:AccountStatus.ACTIVATED)
                   .currency(Math.random()>0.5?"MAD":"USD")
                   .Build();
           save(account);
        }
        System.out.println("******************************");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Account count = "+accountsCount);
        System.out.println("Size: "+bankAccountMap.values().size());
        System.out.println("******************************");
    }
    public static AccountRepositoryImpl getInstance(){
         /* if(accountRepository==null){
            System.out.println("Singleton instantiation");
            accountRepository=new AccountRepositoryImpl();
        }*/
        return accountRepository;
    }
}
