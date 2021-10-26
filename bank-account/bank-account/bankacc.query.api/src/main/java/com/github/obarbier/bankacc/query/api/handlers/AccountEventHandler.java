package com.github.obarbier.bankacc.query.api.handlers;

import com.github.obarbier.bankacc.core.api.events.AccountClosedEvent;
import com.github.obarbier.bankacc.core.api.events.AccountOpenedEvent;
import com.github.obarbier.bankacc.core.api.events.FundsDepositedEvents;
import com.github.obarbier.bankacc.core.api.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvents event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
