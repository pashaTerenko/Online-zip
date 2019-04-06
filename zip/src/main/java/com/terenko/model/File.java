package com.terenko.model;

import javax.persistence.*;

@Entity
@Table(name="File")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    private String name;
    @Column(  columnDefinition="LONGBLOB")
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser User;

    public File(byte[] data,String name,CustomUser us) {
        this.name = name;
        this.data = data;
        this.User=us;
    }
    public File(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomUser getUser() {
        return User;
    }

    public void setUser(CustomUser user) {
        User = user;
    }
}
