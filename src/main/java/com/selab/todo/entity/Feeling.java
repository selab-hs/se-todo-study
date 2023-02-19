package com.selab.todo.entity;

import lombok.AccessLevel;
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
public class Feeling {
    @Id
    @Column(name = "feel")
    private String feel;
}
