package com.bank.AccountAPI.Account.API.Project.Controller;

import com.bank.AccountAPI.Account.API.Project.DTO.AccountDTO;
import com.bank.AccountAPI.Account.API.Project.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    AccountService accountService;

    public AccountController
            (AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDTO> create
            (@RequestBody AccountDTO accountDTO)
    {
        return ResponseEntity.ok
                (accountService.createAccount(accountDTO));
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
        public ResponseEntity<AccountDTO> getAccountById(@PathVariable long id)
        {
          return ResponseEntity.ok(accountService.getById(id));
        }

        @Operation(summary = "Get All Accounts")
        @GetMapping
        public ResponseEntity<List<AccountDTO>> getAllAccounts()
        {
            return ResponseEntity.ok(accountService.getAllAccounts());
        }
        @Operation(summary = "update Account")
        @PutMapping("/{id}")
        public ResponseEntity<AccountDTO> updateAccount
                (@PathVariable long id, @RequestBody AccountDTO accountDTO)
        {
            return ResponseEntity.ok
                    (accountService.updateAccountById(id,accountDTO));
        }

        @Operation(summary = "Delete Account")
        @DeleteMapping("/{id}")
       public ResponseEntity<Void> deleteById(@PathVariable long id)
        {
            accountService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
}
