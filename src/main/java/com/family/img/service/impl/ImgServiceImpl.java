package com.family.img.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.family.config.Constants;
import com.family.img.mapper.ImgGroupInfoMapper;
import com.family.img.mapper.ImgInfoMapper;
import com.family.img.model.ImgGroupInfo;
import com.family.img.model.ImgInfo;
import com.family.img.service.ImgService;
import com.family.memory.model.MemoryPLInfo;
import com.family.memory.model.PlHfInfo;
import com.family.user.mapper.HomeInfoMapper;
import com.family.user.mapper.UserInfoMapper;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author 杜飞龙
 * @date 2020年 03月20日 22:12:11
 * @jdk 1.8
 */
@Transactional
@Service
public class ImgServiceImpl implements ImgService {

    private static  final Logger LOGGER= LoggerFactory.getLogger( ImgServiceImpl.class );

    @Autowired
    private ImgGroupInfoMapper imgGroupInfoMapper;

    @Autowired
    private ImgInfoMapper imgInfoMapper;



    /**
     * @Description: 添加相册
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param zpzmc:
     * @param cjrxh:
     * @param homexh:
     * @param file:
     * @return: void
     **/
    @Override
    public ImgGroupInfo addImgGroup(String zpzmc, String cjrxh, String homexh, MultipartFile file) {
        ImgGroupInfo imgGroupInfo=new ImgGroupInfo();
        imgGroupInfo.setZpzmc( zpzmc );
        imgGroupInfo.setCjrxh( cjrxh );
        imgGroupInfo.setHomexh( homexh );
        imgGroupInfo.setXh( IdUtil.objectId() );
        imgGroupInfo.setCjsj( new Date() );
        //创建相册文件夹 Constants.PHOTO_IMG_PATH+相册xh
        String filePath=Constants.PHOTO_IMG_PATH+"/"+imgGroupInfo.getXh();
        String url=Constants.uploadImg( file,filePath,imgGroupInfo.getXh() );
        if(StringUtils.isNotEmpty( url )) {
            imgGroupInfo.setZpcfmimgurl( url );
        }
        if(imgGroupInfoMapper.insert( imgGroupInfo )<0){
            throw new RuntimeException( "添加相册失败" );
        }
        return  imgGroupInfo;
    }

    @Override
    public void addImg(String zpzxh,String imgdesc,MultipartFile file) {
        ImgInfo imgInfo=new ImgInfo();
        imgInfo.setZpzxh( zpzxh );
        imgInfo.setImgdesc( imgdesc );
        imgInfo.setCjsj( new Date() );
        imgInfo.setXh( IdUtil.objectId() );
        //相片保存路径为相册文件夹 Constants.PHOTO_IMG_PATH+相册xh
        String filePath=Constants.PHOTO_IMG_PATH+"/"+zpzxh;
        String url=Constants.uploadImg( file,filePath,imgInfo.getXh() );
        if(StringUtils.isNotEmpty( url )) {
            imgInfo.setImgurl( url );
        }
        if(imgInfoMapper.insert( imgInfo )<0){
            throw new RuntimeException( "添加相片失败" );
        }
    }

    /**
     * @Description: 查询相册信息
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param imgGroupXh:
     * @return: com.family.img.model.ImgGroupInfo
     **/
    @Override
    public ImgGroupInfo getImgGroupInfo(String imgGroupXh) {
        return imgGroupInfoMapper.selectByPrimaryKey( imgGroupXh );
    }

    /**
     * @Description: 保存相册
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param zpzmc
     * @return: void
     **/
    @Override
    public void saveImgGroup(String imgGroupXh, String zpzmc, MultipartFile file) {
        ImgGroupInfo imgGroupInfo=imgGroupInfoMapper.selectByPrimaryKey( imgGroupXh );
        if(null==imgGroupInfo){
            throw new RuntimeException( "获取相册信息失败" );
        }
        imgGroupInfo.setZpzmc( zpzmc );
        String url=Constants.uploadImg( file,Constants.PHOTO_IMG_PATH );
        if(StringUtils.isNotEmpty( url )) {
            imgGroupInfo.setZpcfmimgurl( url );
        }
        if(imgGroupInfoMapper.updateByPrimaryKey( imgGroupInfo )<0){
            LOGGER.error( "修改相册信息失败,相册序号："+imgGroupXh );
            throw new RuntimeException( "修改相册信息失败" );
        }
    }

    @Override
    public void delImgGroup(String imgGroupXh) {
        if(imgGroupInfoMapper.deleteByPrimaryKey( imgGroupXh )<0){
            LOGGER.error( "删除相册失败,相册序号："+imgGroupXh );
            throw new RuntimeException( "删除相册失败" );
        }
        //删除相册文件夹
        Constants.delFolder( Constants.PHOTO_IMG_PATH+"/"+imgGroupXh );
    }

    @Override
    public void delImg(String imgGroupXh,String imgs) {
        if(StringUtils.isEmpty( imgs )){
            return;
        }
        String [] img=imgs.split( "," );
        for(int i=0;i<img.length;i++){
            if(imgGroupInfoMapper.deleteByPrimaryKey( img[i] )<0){
                LOGGER.error( "删除相片失败,相片序号："+img[i] );
                throw new RuntimeException( "删除相片失败" );
            }
        }
        //删除相册文件夹
        Constants.deleteFile( Constants.PHOTO_IMG_PATH+"/"+imgGroupXh,img );
    }



    @Override
    public PageInfo<ImgGroupInfo> queryImgGroupInfoPageList(String homexh, Integer pageNum, Integer pageSize) {
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        PageHelper.startPage( pageNum, pageSize );
        List<ImgGroupInfo> list = imgGroupInfoMapper.queryImgGroupPageList( homexh );
        PageInfo<ImgGroupInfo> pageInfo = new PageInfo<>( list );
        return pageInfo;
    }

    @Override
    public List<ImgGroupInfo> queryImgGroupInfoAll(String homexh) {
        Example example=new Example(ImgGroupInfo.class);
        Example.Criteria c=example.createCriteria();
        c.andEqualTo( "homexh",homexh );
        return imgGroupInfoMapper.selectByExample( example );
    }

    @Override
    public List<ImgInfo> getImgListByGroupXh(String imgGroupXh) {
        Example example=new Example(ImgInfo.class);
        Example.Criteria c=example.createCriteria();
        c.andEqualTo( "zpzxh",imgGroupXh );
        return imgInfoMapper.selectByExample( example );
    }
}