package com.lgx.dao;

import com.lgx.entity.MediaFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    //将视频信息导入数据库
    boolean saveFileDao(MediaFile media);
}
