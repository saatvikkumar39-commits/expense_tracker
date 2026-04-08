package com.saatvik.expense_tracker.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
