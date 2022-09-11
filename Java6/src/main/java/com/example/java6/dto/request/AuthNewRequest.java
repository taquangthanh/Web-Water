package com.example.java6.dto.request;

import com.example.java6.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthNewRequest {
    @NotNull(message = "Không được để trống")
    private String name;
    @NotNull(message = "Không được để trống")
    private String username;
    @NotNull(message = "Không được để trống")
    private String password;
    @NotNull(message = "Không được để trống")
    private String repeatPassword;
}
