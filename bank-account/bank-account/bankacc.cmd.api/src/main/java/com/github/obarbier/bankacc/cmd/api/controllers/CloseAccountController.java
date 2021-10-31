package com.github.obarbier.bankacc.cmd.api.controllers;

import com.github.obarbier.bankacc.cmd.api.commands.CloseAccountCommand;
import com.github.obarbier.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.github.obarbier.bankacc.core.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/closeBankAccount")
public class CloseAccountController {
    private  final CommandGateway commandGateway;

    @Autowired
    public CloseAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id){
        try{
            var command = CloseAccountCommand.builder()
                                    .id(id)
                                    .build();
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("successfully withdrew funds"), HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Error while Processing request to Close Account for id: " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
