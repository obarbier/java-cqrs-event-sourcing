package com.github.obarbier.bankacc.core.api.events;

import com.github.obarbier.bankacc.core.api.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {
    private String id;
    private  String accountHolderId;
    private AccountType accountType;
    private Date creationDate;
    private double openingBalance;
}
