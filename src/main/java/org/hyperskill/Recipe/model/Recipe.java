package org.hyperskill.Recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    @Id
    Long id;

    @Column
    @NotBlank
    String name;

    @Column
    @NotBlank
    String category;


    @Column
    LocalDateTime date;

    @Column
    @NotBlank
    String description;

    @Column
    @ElementCollection
    @Size(min = 1)
    @NotNull
    List<String> ingredients;

    @Column
    @ElementCollection
    @Size(min = 1)
    @NotNull
    List<String> directions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

}
