package com.github.obarbier.bankacc.query.api.repositories;

import com.github.obarbier.bankacc.core.api.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
}
