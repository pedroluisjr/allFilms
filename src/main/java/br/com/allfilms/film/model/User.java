package br.com.allfilms.film.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

//    private String loginMail;
//    private String password;
//    private String name;
//    private String surname;
//    private String bornDate;


    @Id
    @Column(name = "personId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;

    @Column(name = "loginMail", unique = true, nullable = false)
    private String loginMail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "bornDate", nullable = false)
    private String bornDate;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;
}