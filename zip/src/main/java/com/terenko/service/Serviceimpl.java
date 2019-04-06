package com.terenko.service;
import com.terenko.model.CustomUser;
import com.terenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@org.springframework.stereotype.Service
public class Serviceimpl implements Service {
    @Autowired
    UserRepository ur;

    @Override
    public void addToZip(byte[] file, String name, CustomUser us) {
        us.addToZip(file,name);
        ur.save(us);



    }

    @Override
    public void reset(CustomUser us) {
        us.reset();
        ur.save(us);
    }

    @Override
    public Map<byte[], String> getAll(CustomUser us) {
        return us.getAll();
    }

    @Override

    public byte[] getZip(CustomUser us) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (Map.Entry<byte[], String> reporte : us.getAll().entrySet()) {
            ZipEntry entry = new ZipEntry(reporte.getValue());
            entry.setSize(reporte.getKey().length);
            zos.putNextEntry(entry);
            zos.write(reporte.getKey());
        }
        zos.closeEntry();
        zos.close();

        return baos.toByteArray();

    }
}
