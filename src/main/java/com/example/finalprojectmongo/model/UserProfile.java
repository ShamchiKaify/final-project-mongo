package com.example.finalprojectmongo.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id @Indexed
    private String id;
    @NotNull(message = "Username cannot be null")
    private String userName;
    private String password;
    private String fullName;
    private String dateOfBirth;
    @Indexed
    private String nid;
    @NotBlank(message = "Role cannot be null")
    private ROLE role;
}

