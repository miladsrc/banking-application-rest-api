package bankingapp.service.impl;

import bankingapp.dto.AccountDto;
import bankingapp.entity.Account;
import bankingapp.mapper.AccountMapper;
import bankingapp.repostiroy.AccountRepository;
import bankingapp.service.AccountService;
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
}
