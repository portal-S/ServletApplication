package com.portal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.repository.FileRepositoryImpl;
import com.portal.repository.interfaces.FileRepository;
import com.portal.utils.BDUtil;
import sun.net.www.URLConnection;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

public class FileService {

    FileRepository fileRepository = new FileRepositoryImpl();

    public void readAll(HttpServletResponse response){
        try {
            Writer writer = response.getWriter();
            for (File file : fileRepository.readAll()){
                writer.write(file.getName());
                writer.write("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void create(InputStream stream, String filename){
        int id = FileRepositoryImpl.getMaxId() + 1;
        try {
            String path = BDUtil.path + "/" + id + "_"  + filename;
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            fileRepository.create(new File(path));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        fileRepository.delete(id);
    }

    public void read(int id, HttpServletResponse response){
        try {
            File file = fileRepository.read(id);
            System.out.println(file.getAbsolutePath());
            ServletOutputStream out = response.getOutputStream();
            byte[] byteArray = Files.readAllBytes(file.toPath());
            response.setContentType(Files.probeContentType(file.toPath()));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            out.write(byteArray);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
