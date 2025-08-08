package com.bank.AccountAPI.Account.API.Project.Repository;

import com.bank.AccountAPI.Account.API.Project.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>
{

}
