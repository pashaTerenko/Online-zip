package com.terenko.model;

import javax.persistence.*;
//WIP
@Entity
@Table(name="Zip")
public class zipFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    @Column
    String name;
    @Column(  columnDefinition="LONGBLOB")
    private byte[] data;
    @ManyToOne
    @JoinColumn(name="user_id")
    private CustomUser user;
    public zipFile(){}

    public zipFile(byte[] data,String name,CustomUser user) {
        this.data = data;
        this.name=name;
        setUser(user);
    }

    public long getId() {
        return id;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
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
}
