package com.project.avm.model;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public File() {
    }

    @Column(name="name", length=50, nullable=false, unique=false)
    private String name;
    @Column(name="path", length=500, nullable=false, unique=false)
    private String path;
    @Column(name="size", length=500, nullable=false, unique=false)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public File(String name, String path, String size, User user) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public File(String path, String size, String name) {
        this.path = path;
        this.size = size;
        this.name = name;
    }

}
