package com.github.obarbier.bankacc.cmd.api.controllers;

import com.github.obarbier.bankacc.cmd.api.commands.DepositFundsCommand;
import com.github.obarbier.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.github.obarbier.bankacc.cmd.api.dto.OpenAccountResponse;
import com.github.obarbier.bankacc.core.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/withdrawFunds")
public class WithdrawFundsController {
    private  final CommandGateway commandGateway;

    @Autowired
    public WithdrawFundsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id, @Valid @RequestBody WithdrawFundsCommand command){
        try{
            command.setId(id);
            commandGateway.send(command).get(); // block until the request respond
            return new ResponseEntity<>(new BaseResponse("successfully withdrew funds"), HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Error while Processing request to withdraw Funds for id: " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
