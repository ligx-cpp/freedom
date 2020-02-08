package com.lgx.controller;

import com.lgx.entity.Result;
import com.lgx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @GetMapping("/to")
    public String upload1(){
        return "upload1";
    }
    @GetMapping("/aa")
    public String broadcast(){
        return "broadcast";
    }
    //上传文件
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile(HttpServletRequest request, String guid, Integer chunk, MultipartFile file){

        return fileService.file_upload(request,guid,chunk,file);
    }

    @RequestMapping(value = "/merge",method = RequestMethod.POST)
    @ResponseBody
    public Result mergeFile(String guid, String fileName) {

        return fileService.file_merge(guid,fileName);
    }
}
