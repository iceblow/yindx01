package com.uncleserver.dao;

import com.uncleserver.model.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}