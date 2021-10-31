package com.github.obarbier.bankacc.query.api.handlers;

import com.github.obarbier.bankacc.query.api.dto.AccountLookupResponse;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
