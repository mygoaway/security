package inflearn.spring.securty.practice.security.service;

import inflearn.spring.securty.practice.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Account account;

    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUserName(), account.getPassword(), authorities);
        this.account = account; // 멤버변수로 참조하기 위해 Account 객체 등록
    }
}
