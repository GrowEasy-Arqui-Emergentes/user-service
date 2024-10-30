package com.groweasy.userservice.GrowEasy.UsersContext.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length =150, nullable=false)
    private String name;

    @Column(name="image", length =200, nullable=false)
    private String image;

    @Column(name="description", length =150, nullable=false)
    private String description;

    @Column(name="price", length =6, nullable=false)
    private String price;

    @Column(name="rating", length =3, nullable=false)
    private String rating;

    @Column(name="duration", length =3, nullable=false)
    private String duration;

    @Column(name="date", length =30, nullable=false)
    private String date;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;
}
