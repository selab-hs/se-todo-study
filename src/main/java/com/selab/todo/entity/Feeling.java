package com.selab.todo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "feeling")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Feeling {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "feel")
    private String feel;
}
