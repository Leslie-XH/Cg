package com.changgou.file.service;

import com.changgou.file.util.FastDFSFile;
import com.changgou.pojo.FileInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Wuhz
 * @date 2021-4-29 11:33
 */
public interface FileInfoService {

    FileInfo uploadFile(FastDFSFile file);

    List<FileInfo> findList(Map<String, Object> searchMap);

    List<FileInfo> findPage(int page, int size);

    List<FileInfo> findPage(Map<String, Object> searchMap, int page, int size);

    void add(FileInfo fileInfo);

    void delete(Long id);

    FileInfo findById(long id);

    void update(FileInfo fileInfo);

}
