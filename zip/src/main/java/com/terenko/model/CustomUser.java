package com.terenko.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String login;
    private String password;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<zipFile>  zip=new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
     @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    List<File> FilesToZip=new ArrayList<>();

    public CustomUser(String login, String password) {
        this.login = login;
        this.password = password;


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
    public void addZip(byte[] file) {
        zip.add(new zipFile(file,"YourArchive"+zip.size(),this));

    }


    public void resetZip() {
        for(zipFile f:zip){
            f.setUser(null);
        }
    }


    public Map<Long , String>  getAllZip(){
        Map<Long, String> zips=new HashMap<Long, String>();
        for(zipFile f:zip){
            zips.put(f.getId(),f.getName());
        }
        return zips;
    }
    public byte[] getZip(Long id){
        for(zipFile f:zip){
            if(f.getId()==id)return f.getData();
        }
        return null;
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
