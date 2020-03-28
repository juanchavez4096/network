package com.message.system.network.repository;

import com.message.system.network.document.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UsersRepository extends ReactiveMongoRepository<Users, String> {
}
