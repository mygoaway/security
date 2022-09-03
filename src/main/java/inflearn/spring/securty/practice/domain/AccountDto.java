package inflearn.spring.securty.practice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class AccountDto {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String age;
    private String roles;

}
