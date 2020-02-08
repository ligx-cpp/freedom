package com.lgx.service;


import com.lgx.dao.FileMapper;
import com.lgx.entity.MediaFile;
import com.lgx.entity.Result;
import com.lgx.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    // 保存文件的根目录
    public Result file_upload(HttpServletRequest request, String guid, Integer chunk, MultipartFile file){
        try {
            String projectUrl = "E:/video/";//路径写死
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                if (chunk == null) chunk = 0;
                // 临时目录用来存放所有分片文件
                String tempFileDir = projectUrl + guid;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
            }
        } catch (Exception e) {
            return Result.failMessage(400,e.getMessage());
        }
        return Result.successMessage(200,"上次成功");
    }
    public Result file_merge(String guid, String fileName){
        // 得到 destTempFile 就是最终的文件
        String projectUrl = "E:/video/";
        try {
            String sname = fileName.substring(fileName.lastIndexOf("."));
            //时间格式化格式
            Date currentTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //获取当前时间并作为时间戳
            String timeStamp = simpleDateFormat.format(currentTime);
            //拼接新的文件名
            String newName = timeStamp + sname;
            simpleDateFormat = new SimpleDateFormat("yyyyMM");
            String path = projectUrl;
            String tmp = simpleDateFormat.format(currentTime);
            File parentFileDir = new File(path + guid);
            if (parentFileDir.isDirectory()) {
                File destTempFile = new File(path + tmp, newName);
                if (!destTempFile.exists()) {
                    //先得到文件的上级目录，并创建上级目录，在创建文件
                    destTempFile.getParentFile().mkdir();
                    try {
                        destTempFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                long length_sum=0;
                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, guid + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                    //遍历"所有分片文件"到"最终文件"中
                    FileUtils.copyFile(partFile, destTempfos);
                    long length = partFile.length();//计算每一块的大小
                    length_sum+=length;
                    destTempfos.close();
                }
                String printSize = Utils.getPrintSize(length_sum);//获得文件大小
                // 删除临时目录中的分片文件
                FileUtils.deleteDirectory(parentFileDir);
                int broadcnt=0;//播放次数
                MediaFile media=new MediaFile();
                media.setId(guid);
                media.setMedianame(fileName);
                media.setCreatetime(currentTime);
                media.setMediasize(printSize);
                media.setBroadcnt(broadcnt);
                fileMapper.saveFileDao(media);
                return Result.successMessage(200,"合并成功");
            }else{
                return Result.failMessage(400,"没找到目录");
            }
        } catch (Exception e) {
            return Result.failMessage(400,e.getMessage());
        }
    }
}
