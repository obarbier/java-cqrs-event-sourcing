package com.github.obarbier.bankacc.query.api.dto;

import com.github.obarbier.bankacc.core.api.dto.BaseResponse;
import com.github.obarbier.bankacc.core.api.models.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;
    public AccountLookupResponse(String message){
        super(message);
    }

    public AccountLookupResponse(String message, List<BankAccount> accounts){
        super(message);
        this.accounts = accounts;
    }
    public AccountLookupResponse(String message, BankAccount account){
        super(message);
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
    }

    public void setAccounts(List<BankAccount> accounts){
        this.accounts = accounts;
    }

    public List<BankAccount> getAccounts(){
        return this.accounts;
    }
}
