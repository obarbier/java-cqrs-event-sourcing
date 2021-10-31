package com.github.obarbier.bankacc.query.api.handlers;

import com.github.obarbier.bankacc.core.api.models.BankAccount;
import com.github.obarbier.bankacc.query.api.dto.AccountLookupResponse;
import com.github.obarbier.bankacc.query.api.dto.EqualityType;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAllAccountsQuery;
import com.github.obarbier.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        var response = bankAccount.isPresent()
                ? new AccountLookupResponse("Bank Account Successfully Returned", bankAccount.get())
                : new AccountLookupResponse("No Bank Account Found for Id: " + query.getId());
        return response ;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {

        var bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
        var response = bankAccount.isPresent()
                ? new AccountLookupResponse("Bank Account Successfully Returned", bankAccount.get())
                : new AccountLookupResponse("No Bank Account Found for Holder Id: " + query.getAccountHolderId());
        return response ;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();

        if(!bankAccountIterator.iterator().hasNext()){
            return new AccountLookupResponse("No Bank Account Found");
        }

        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountIterator.forEach(i-> bankAccounts.add(i));

        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully Returned " + count+" Bank Account Found", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query) {
        var bankAccounts =  query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());
        var response = bankAccounts != null && bankAccounts.size() > 0
                ? new AccountLookupResponse("Bank Account Successfully Returned",bankAccounts)
                : new AccountLookupResponse("No Bank Account Found");
        return  response;
        }

}
