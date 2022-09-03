package inflearn.spring.securty.practice.service.Impl;

import inflearn.spring.securty.practice.domain.Account;
import inflearn.spring.securty.practice.repository.UserRepository;
import inflearn.spring.securty.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
