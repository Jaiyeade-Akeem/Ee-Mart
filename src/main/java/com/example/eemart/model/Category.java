package com.example.eemart.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Builder
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name")
    private @NotBlank String categoryName;

    private @NotBlank String description;

    private @NotBlank String imageUrl;
}
