package com.mercadolibre.bookmark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "ml_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;
    @Column
    private String mId;
    @Column
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "user_item",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
//    private Set<Item> items;
}
