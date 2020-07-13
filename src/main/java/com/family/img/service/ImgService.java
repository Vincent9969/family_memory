package com.family.img.service;

import com.family.img.model.ImgGroupInfo;
import com.family.img.model.ImgInfo;
import com.family.memory.model.MemoryPLInfo;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 杜飞龙
 * @date 2020年 03月20日 22:10:52
 * @jdk 1.8
 */
public interface ImgService {

    public ImgGroupInfo addImgGroup(String zpzmc, String cjrxh, String homexh, MultipartFile file);

    public void addImg(String zpzxh,String imgdesc,MultipartFile file);

    public ImgGroupInfo getImgGroupInfo(String imgGroupXh);

    public void saveImgGroup(String imgGroupXh, String zpzmc, MultipartFile file);

    public void delImgGroup(String imgGroupXh);

    public void delImg(String imgGroupXh,String imgs);

    public PageInfo<ImgGroupInfo> queryImgGroupInfoPageList(String homexh, Integer pageNum, Integer pageSize);

    public List<ImgGroupInfo> queryImgGroupInfoAll(String homexh);

    public List<ImgInfo> getImgListByGroupXh(String imgGroupXh);
}