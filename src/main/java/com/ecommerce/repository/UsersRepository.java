package com.ecommerce.repository;

import com.ecommerce.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	UsersEntity findByUserNameOrEmailIgnoreCase(String userName, String email);

	UsersEntity findByUserNameIgnoreCase(String userName);
}
