package com.github.obarbier.user.query.api.repositories;

import com.github.obarbier.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
