package com.bank.AccountAPI.Account.API.Project.Service;

import com.bank.AccountAPI.Account.API.Project.DTO.AccountDTO;
import com.bank.AccountAPI.Account.API.Project.DTO.KycResponse;
import com.bank.AccountAPI.Account.API.Project.Entity.Account;
import com.bank.AccountAPI.Account.API.Project.Exceptions.ResourceNotFoundException;
import com.bank.AccountAPI.Account.API.Project.Mapper.AccountMapper;
import com.bank.AccountAPI.Account.API.Project.Repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

     AccountRepository accountRepository;
    private  final KycVerificationService kycVerificationService;
     AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, KycVerificationService kycVerificationService, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.kycVerificationService = kycVerificationService;
        this.accountMapper = accountMapper;
    }

    public AccountDTO createAccount(AccountDTO accountDTO)
  {
      KycResponse kycResponse = kycVerificationService.verifyCustomer(accountDTO.getCustomerId());
      if (!"VERIFIED".equalsIgnoreCase(kycResponse.getStatus())) {
          throw new RuntimeException("KYC verification failed: " + kycResponse.getMessage());
      }
accountDTO.setKycStatus(kycResponse.getStatus());

      Account account = accountMapper.toentity(accountDTO);

      return accountMapper.toDTO(accountRepository.save(account));
  }

    public AccountDTO getById(long id) {
       Account account= accountRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return accountMapper.toDTO(account);
    }

    public AccountDTO updateAccountById(long id, AccountDTO accountDTO)
    {
       Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

existing.setKycStatus(accountDTO.getKycStatus());
existing.setBalance(accountDTO.getBalance());
existing.setName(accountDTO.getName());
existing.setEmail(accountDTO.getEmail());
return accountMapper.toDTO(accountRepository.save(existing));
    }

    public void deleteById(long id) {
        accountRepository.deleteById(id);
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts =accountRepository.findAll();
       return accounts.stream().map(accountMapper::toDTO).collect(Collectors.toList());
    }
}


