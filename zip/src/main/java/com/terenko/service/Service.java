package com.terenko.service;

import com.terenko.model.CustomUser;

import java.io.IOException;
import java.util.Map;

public interface Service {
    void addToZip(byte[] file, String name, CustomUser us );
    void reset(CustomUser us);
    Map<byte[],String> getAll(CustomUser us);
    void getZip(CustomUser us) throws IOException;
    void resetZip(CustomUser us);
    Map<Long,String> getAllZip(CustomUser us);
    byte[] getZip(CustomUser us,long id) throws IllegalArgumentException;

}
