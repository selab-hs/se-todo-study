package com.selab.todo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "feeling")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Feeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "feel")
    private String feel;
}
