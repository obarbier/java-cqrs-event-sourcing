package com.github.obarbier.user.query.api.handlers;

import com.github.obarbier.user.query.api.dto.UserLookupResponse;
import com.github.obarbier.user.query.api.queries.FindAllUsersQuery;
import com.github.obarbier.user.query.api.queries.FindUserByIdquery;
import com.github.obarbier.user.query.api.queries.SearchUserQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdquery query);
    UserLookupResponse searchUsers(SearchUserQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}