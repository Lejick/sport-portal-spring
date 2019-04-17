package org.portal.back.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoginsRepository extends CrudRepository<Logins, Long> {
    List<Logins> findByLogin(String username);
}
