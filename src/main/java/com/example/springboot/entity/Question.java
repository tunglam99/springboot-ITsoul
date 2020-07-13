package com.example.springboot.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "QUESTION")
public class Question {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "STATUS")
    private boolean status;

    @ManyToOne
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
