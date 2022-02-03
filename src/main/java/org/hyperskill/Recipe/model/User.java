package org.hyperskill.Recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column
    @Pattern(regexp = "[0-9a-zA-Z.]+\\@[a-zA-Z]+\\.[a-zA-Z]+")
    private String email;

    @Column
    @NotBlank
    @Size(min = 8)
    private String password;

}
