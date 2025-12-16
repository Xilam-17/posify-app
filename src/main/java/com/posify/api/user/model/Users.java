package com.posify.api.user.model;

import com.posify.api.user.request.UserRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public static Users mapToEntity(UserRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        return user;
    }
}