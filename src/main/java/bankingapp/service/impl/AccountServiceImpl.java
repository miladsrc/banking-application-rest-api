package bankingapp.service.impl;

import bankingapp.dto.AccountDto;
import bankingapp.entity.Account;
import bankingapp.mapper.AccountMapper;
import bankingapp.repostiroy.AccountRepository;
import bankingapp.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    // we don't use @Autowired because 4.3 forte , spring
    // automatically adds dependencies for a single parameter
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    @Transactional
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));

        double accountBalance = account.getBalance();
        accountBalance+=amount;
        account.setBalance(accountBalance);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist!"));

        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount!");
        }

        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account accountSaved = accountRepository.save(account);


        return AccountMapper.mapToAccountDto(accountSaved);
    }


}
