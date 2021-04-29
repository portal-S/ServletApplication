package com.portal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.model.Account;
import com.portal.model.Event;
import com.portal.model.FileBD;
import com.portal.model.User;
import com.portal.repository.FileRepositoryImpl;
import com.portal.repository.UserRepositoryImpl;
import com.portal.repository.interfaces.FileRepository;
import com.portal.repository.interfaces.UserRepository;
import com.portal.utils.BDUtil;
import sun.net.www.URLConnection;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

public class FileService {

    private FileRepository fileRepository = new FileRepositoryImpl();
    private AccountService accountService = new AccountService();
    private UserService userService = new UserService();
    private EventService eventService = new EventService();

    public void readAll(HttpServletResponse response){
        try {
            Writer writer = response.getWriter();
            for (FileBD file : fileRepository.readAll()){
                writer.write(file.toString());
                writer.write("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void create(InputStream stream, String filename, int userId){
        int id = FileRepositoryImpl.getMaxId() + 1;
        User user = userService.read(userId);
        createFile(id, stream, filename);
        fileRepository.create(new FileBD(filename,user));
        eventService.create(new Event(user, "UPLOAD FILE " + filename));
    }

    public void update(InputStream stream, String filename, int id){
        fileRepository.update(new FileBD(id, filename, new User(new Account())));
        createFile(id, stream, filename);
    }

    public void delete(int id){
        fileRepository.delete(id);
    }

    public void read(int id, HttpServletResponse response, int userId){
        try {
            FileBD fileBD = fileRepository.read(id);
            User user = fileBD.getUser();
            System.out.println("WORK1");
            if(user.getId() == userId){
                System.out.println("WORK2");
                File file = new File(BDUtil.path + "/" +fileBD.getId() + "_" + fileBD.getName());
                eventService.create(new Event(user, "DOWNLOAD FILE " + fileBD.getName()));
                ServletOutputStream out = response.getOutputStream();
                byte[] byteArray = Files.readAllBytes(file.toPath());
                response.setContentType(Files.probeContentType(file.toPath()));
                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                out.write(byteArray);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile(int id, InputStream stream, String filename){
        try {
            String path = BDUtil.path + "/" + id + "_"  + filename;
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
