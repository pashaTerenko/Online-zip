package com.terenko.service;

import com.terenko.model.CustomUser;

import java.io.IOException;
import java.util.Map;

public interface Service {
    void addToZip(byte[] file, String name, CustomUser us );
    void reset(CustomUser us);
    Map<byte[],String> getAll(CustomUser us);
    byte[] getZip(CustomUser us) throws IOException;

}
