package com.portal.controller;

import com.portal.repository.FileRepositoryImpl;
import com.portal.repository.interfaces.FileRepository;
import com.portal.service.FileService;
import com.portal.utils.BDUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/files/*"})
@MultipartConfig()
public class FileServlet extends HttpServlet {

    private File file;
    private  String PATH;
    private FileService service;


    @Override
    public void init() throws ServletException {
        PATH = getServletContext().getRealPath("/" + "WEB-INF/classes/files" + File.separator);
        BDUtil.path = PATH;
        file = new File(PATH);
        service = new FileService();
       // file = new File(getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\files\\");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] info = req.getRequestURI().split("/");
        System.out.println(info[1]);
        System.out.println(info.length);
        if(info.length >= 3 && hasNumber(info[2])){
            service.read(Integer.parseInt(info[2]), resp);
            return;
        }
        Writer writer = resp.getWriter();
        writer.write("FILES INFO");
        writer.write("\n");
        service.readAll(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file-name");
        service.create(part.getInputStream(), part.getSubmittedFileName());
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        service.delete(Integer.parseInt(req.getParameter("delete-file-id")));
        resp.getWriter().write("DELETED");
    }

    private boolean hasNumber(String s){
        try {
            Integer.valueOf(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
