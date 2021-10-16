package com.github.obarbier.user.query.api.handlers;

import com.github.obarbier.user.query.api.dto.UserLookupResponse;
import com.github.obarbier.user.query.api.queries.FindAllUsersQuery;
import com.github.obarbier.user.query.api.queries.FindUserByIdquery;
import com.github.obarbier.user.query.api.queries.SearchUserQuery;
import com.github.obarbier.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
    private  final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdquery query) {
        var users = userRepository.findById(query.getId()).orElse(null);
        return  new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUserQuery query) {
        var users = new ArrayList<>(userRepository.findByFilterRegex(query.getSearch()));
        return  new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return  new UserLookupResponse(users);
    }
}
