package com.terenko.model;

import javax.persistence.*;
//WIP
@Entity
@Table(name="Zip")
public class zipFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    @Column(  columnDefinition="LONGBLOB")
    private byte[] data;
  /*  @ManyToOne(mappedBy = "zip")
    private CustomUser user;*/
    public zipFile(){}

    public zipFile(byte[] data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }



    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


}
