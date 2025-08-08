package com.bank.AccountAPI.Account.API.Project.Mapper;

import com.bank.AccountAPI.Account.API.Project.DTO.AccountDTO;
import com.bank.AccountAPI.Account.API.Project.Entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

AccountDTO toDTO (Account account);

Account toentity(AccountDTO dto);
}
