package com.terenko.service;
import com.terenko.model.CustomUser;
import com.terenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@org.springframework.stereotype.Service
public class Serviceimpl implements Service {
    @Autowired
    UserRepository ur;
    @Autowired
    TaskExecutor taskExecutor;

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

    public void getZip(CustomUser us) throws IOException {
    taskExecutor.execute(new Runnable() {
    @Override
    public void run() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        try {
            for (Map.Entry<byte[], String> reporte : us.getAll().entrySet()) {
                ZipEntry entry = new ZipEntry(reporte.getValue());
                entry.setSize(reporte.getKey().length);
                zos.putNextEntry(entry);
                zos.write(reporte.getKey());
            }
            zos.closeEntry();
            zos.close();
            us.addZip(baos.toByteArray());
            ur.save(us);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
});



    }

    @Override
    public void resetZip(CustomUser us) {
        us.resetZip();
        ur.save(us);
    }

    @Override
    public Map<Long, String> getAllZip(CustomUser us) {
        return us.getAllZip();
    }

    @Override
    public byte[] getZip(CustomUser us, long id)throws IllegalArgumentException  {
        byte[] zip=us.getZip(id);
        if (zip!=null)return zip;
        else throw new IllegalArgumentException();
    }
}
