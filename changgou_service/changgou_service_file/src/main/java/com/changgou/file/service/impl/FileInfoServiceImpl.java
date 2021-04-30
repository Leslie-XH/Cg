package com.changgou.file.service.impl;

import com.changgou.file.dao.FileInfoMapper;
import com.changgou.file.service.FileInfoService;
import com.changgou.file.util.FastDFSClient;
import com.changgou.file.util.FastDFSFile;
import com.changgou.pojo.FileInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wuhz
 * @date 2021-4-29 11:32
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public FileInfo uploadFile(FastDFSFile file) {

        Example example = new Example(FileInfo.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("id", "123");


        String[] upload = FastDFSClient.upload(file);
        com.changgou.pojo.FileInfo fileInfo = new com.changgou.pojo.FileInfo();
        fileInfo.setDelFlag("0");
        fileInfo.setFileName(file.getName());
        fileInfo.setFilePathUrl(upload[0]+"/"+upload[1]);
//        fileInfo.setFileSize(file.get);
        fileInfo.setLastUpdateTime(new Date());
        fileInfo.setFileType(file.getExt());
        int i = fileInfoMapper.insertSelective(fileInfo);
        System.out.println(fileInfo.getId());
        return fileInfo;
    }

    @Override
    public List<FileInfo> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        List<FileInfo> fileInfos = fileInfoMapper.selectByExample(example);
        return fileInfos;
    }

    @Override
    public Page<FileInfo> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        return (Page<FileInfo>)fileInfoMapper.selectAll();
    }

    @Override
    public List<FileInfo> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap);
        PageHelper.startPage(page,size);
        return (Page<FileInfo>)fileInfoMapper.selectByExample(example);
    }

    @Override
    public void add(FileInfo fileInfo) {
        fileInfoMapper.insert(fileInfo);
    }

    @Override
    public void delete(Long id) {
        fileInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public FileInfo findById(long id) {
        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
        return fileInfo;
    }

    @Override
    public void update(FileInfo fileInfo) {
        fileInfoMapper.updateByPrimaryKeySelective(fileInfo);
    }

    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(FileInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap.get("id") != null && !"".equals(searchMap.get("id"))){
            criteria.andEqualTo("id", searchMap.get("id"));
        }
        if(searchMap.get("fileName") != null && !"".equals(searchMap.get("fileName"))){
            criteria.andLike("id", "%" + searchMap.get("fileName") + "%");
        }
        return example;
    }

}
