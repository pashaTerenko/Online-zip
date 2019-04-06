package com.terenko.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String login;
    private String password;



   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Zips")
    private zipFile zip;*///WIP
     @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    List<File> FilesToZip=new ArrayList<>();

    public CustomUser(String login, String password) {
        this.login = login;
        this.password = password;
        /*this.zip=new zipFile();*/

    }



    public CustomUser() {}
    public void addToZip(byte[] file, String name) {
                FilesToZip.add(new File(file,name,this));

    }


    public void reset() {
        for(File f:FilesToZip){
            f.setUser(null);
        }
    }


    public Map<byte[], String> getAll(){
    Map<byte[], String> files=new HashMap<byte[], String>();
    for(File f:FilesToZip){
        files.put(f.getData(),f.getName());
    }
    return files;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
