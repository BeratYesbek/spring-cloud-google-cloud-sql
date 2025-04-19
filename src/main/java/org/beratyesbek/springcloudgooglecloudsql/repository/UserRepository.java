package org.beratyesbek.springcloudgooglecloudsql.repository;

import org.beratyesbek.springcloudgooglecloudsql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
