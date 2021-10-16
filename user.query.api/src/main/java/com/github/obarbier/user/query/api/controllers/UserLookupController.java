package com.github.obarbier.user.query.api.controllers;

import com.github.obarbier.user.query.api.dto.UserLookupResponse;
import com.github.obarbier.user.query.api.queries.FindAllUsersQuery;
import com.github.obarbier.user.query.api.queries.FindUserByIdquery;
import com.github.obarbier.user.query.api.queries.SearchUserQuery;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.catalina.User;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ap1/v1/userLookup")
public class UserLookupController {

    private  final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    public ResponseEntity<UserLookupResponse> getAllUsers(){
        try{
            var query = new FindAllUsersQuery();
            var response =  queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null){
                return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "failed to complete get all users requests";
            System.out.println(e.toString());
            return  new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<UserLookupResponse> getUserByid(@PathVariable(value = "id") String id){
        try{
            var query = new FindUserByIdquery(id);
            var response =  queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null){
                return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "failed to complete get user by id requests";
            System.out.println(e.toString());
            return  new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/filter/{filter}")
    public ResponseEntity<UserLookupResponse> getUserByFilter(@PathVariable(value = "filter") String filter){
        try{
            var query = new SearchUserQuery(filter);
            var response =  queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null){
                return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            var safeErrorMessage = "failed to complete user search requests";
            System.out.println(e.toString());
            return  new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
