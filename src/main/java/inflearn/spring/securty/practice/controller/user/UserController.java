package inflearn.spring.securty.practice.controller.user;

import inflearn.spring.securty.practice.domain.Account;
import inflearn.spring.securty.practice.domain.AccountDto;
import inflearn.spring.securty.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/mypage")
    public String myPage() throws Exception {
        return "user/mypage";
    }

    @GetMapping("/accounts")
    public String createUser() throws Exception {
        return "user/login/register";
    }

    @PostMapping("/admin/accounts")
    public String createUser(AccountDto accountDto)throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        userService.createUser(account);

        return "redirect:/";
    }
}
