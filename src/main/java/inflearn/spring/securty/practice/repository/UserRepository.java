package inflearn.spring.securty.practice.repository;

import inflearn.spring.securty.practice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
