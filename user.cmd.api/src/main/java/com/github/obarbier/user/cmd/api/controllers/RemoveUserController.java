package com.github.obarbier.user.cmd.api.controllers;

import com.github.obarbier.user.cmd.api.commands.RemoveUserCommand;
import com.github.obarbier.user.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/removeUser")
public class RemoveUserController {

    public final CommandGateway commandGateway;

    @Autowired
    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable(value = "id") String id) {
        try {
            commandGateway.sendAndWait(new RemoveUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("user successfully removed!"), HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "Error while removing user request for id: "+ id;
            System.out.println(e.toString());
            return  new ResponseEntity<>( new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
