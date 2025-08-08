package com.bank.AccountAPI.Account.API.Project.service;

import com.bank.AccountAPI.Account.API.Project.DTO.AccountDTO;
import com.bank.AccountAPI.Account.API.Project.Entity.Account;
import com.bank.AccountAPI.Account.API.Project.Repository.AccountRepository;
import com.bank.AccountAPI.Account.API.Project.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

   @Test
    void createAccountDTOTest() {
        Account account = new Account();
        AccountDTO accountDTO = new AccountDTO();
        account.setCustomerId("123");
        account.setName("John Doe");

        account.setBalance(1000.0);
        account.setEmail("Johndoe@gmail.com");
        account.setKycStatus("VERIFIED");

       Mockito.when(accountRepository.save(account))
                .thenReturn(account);

        AccountDTO addedAccount =
                accountService.createAccount(accountDTO);

assertNotNull(addedAccount);

    }
}