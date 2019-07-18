package com.myapp.entity;

import lombok.*;

import javax.persistence.*;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User(String username, String password, String phoneNumber, String email, RoleType role) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }
}
