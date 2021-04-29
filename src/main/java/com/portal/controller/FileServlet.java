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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.read(Integer.parseInt(req.getParameter("file-id")), resp, Integer.parseInt(req.getParameter("user-id")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        service.create(part.getInputStream(), part.getSubmittedFileName(), Integer.parseInt(req.getParameter("user-id")));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Integer.parseInt(req.getParameter("delete-file-id")));
        resp.getWriter().write("DELETED");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        service.delete(Integer.parseInt(req.getParameter("delete-file-id")));
        service.create(part.getInputStream(), part.getSubmittedFileName(), Integer.parseInt(req.getParameter("account-id")));
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
