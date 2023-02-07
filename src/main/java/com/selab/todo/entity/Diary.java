package com.selab.todo.entity;

import com.selab.todo.common.BaseEntity;
import com.selab.todo.model.Feeling;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    @Column(name = "feel")
    private Feeling feel;


    public Diary(String title, String content, Feeling feel, int year, int month, int day) {
        this.title = title;
        this.content = content;
        this.feel = feel;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void update(String title, String content, Feeling feel) {
        this.title = title;
        this.content = content;
        this.feel = feel;
    }
}
