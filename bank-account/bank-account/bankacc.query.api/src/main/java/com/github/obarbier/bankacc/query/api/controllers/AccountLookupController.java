package com.github.obarbier.bankacc.query.api.controllers;

import com.github.obarbier.bankacc.core.api.dto.BaseResponse;
import com.github.obarbier.bankacc.query.api.dto.AccountLookupResponse;
import com.github.obarbier.bankacc.query.api.dto.EqualityType;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountByIdQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.github.obarbier.bankacc.query.api.queries.FindAllAccountsQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accountLookup")
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){
        try{
            var query = new FindAllAccountsQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if(response == null || response.getAccounts()==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Failed to complete get All Accounts";
            System.out.println(e.toString());
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getById(@PathVariable(value = "id") String id){
        try{
            var query = new FindAccountByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if(response == null || response.getAccounts()==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Failed to complete get Account By id";
            System.out.println(e.toString());
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byAccountHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getByAccountHolderId(@PathVariable(value = "accountHolderId") String accountHolderId){
        try{
            var query = new FindAccountByHolderIdQuery(accountHolderId);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if(response == null || response.getAccounts()==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Failed to complete get Account By account holder id";
            System.out.println(e.toString());
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable(value = "equalityType") EqualityType equalityType, @PathVariable(value = "balance") double balance){
        try{
            var query = new FindAccountsWithBalanceQuery(equalityType, balance);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            if(response == null || response.getAccounts()==null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Failed to complete get Account With Balance";
            System.out.println(e.toString());
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
