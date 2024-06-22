package bankingapp.service;

import bankingapp.dto.AccountDto;
import bankingapp.entity.Account;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
}
