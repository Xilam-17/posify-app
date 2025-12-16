package com.posify.api.user.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private Long id;
    private String username;
    private String password;
}
