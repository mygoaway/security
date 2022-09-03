package inflearn.spring.securty.practice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data // lombok에서 제공하는 기능, getter / setter / toString 기능 제공
public class Account {

    @Id @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String age;
    private String roles;

}
