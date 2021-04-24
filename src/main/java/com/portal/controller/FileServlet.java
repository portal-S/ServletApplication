package com.portal.controller;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/files"})
public class FileServlet extends HttpServlet {

    private File file;

    @Override
    public void init() throws ServletException {
        file = new File(getServletContext().getRealPath("/") + "/WEB-INF/classes/files/");
        //file = new File(getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\files\\");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(file.exists()) resp.getWriter().write("EXIST");
        for (File f : file.listFiles()){
            resp.getWriter().write(f.getName());
        }
        resp.getWriter().write("\n");
        resp.getWriter().write(file.getAbsolutePath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(file);
        diskFileItemFactory.setSizeThreshold(100 * 1024);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(100 * 1024);

        List fileItems = null;
        File file1;
        try {
            fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {

                    String fileName = fileItem.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file1 = new File(file.getAbsolutePath() + "/" +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file1 = new File(file.getAbsolutePath() + "/" +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                        fileItem.write(file1);
                    resp.getWriter().write(file1.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
