package com.ppc.blog.account;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ppc.blog.account.AccountEntity;

@Repository
public interface AccountDAO
extends JpaSpecificationExecutor<AccountEntity>,
        CrudRepository<AccountEntity, String> {
  List<AccountEntity> findByUserNameOrEmail(String userName, String email);
  List<AccountEntity> findByUserName(String userName);
  AccountEntity findOneById(String id);
}
