package com.family.memory.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.family.config.Constants;
import com.family.config.MultipartFileToFile;
import com.family.memory.mapper.MemoryInfoMapper;
import com.family.memory.mapper.MemoryPLInfoMapper;
import com.family.memory.mapper.PlHfInfoMapper;
import com.family.memory.model.MemoryInfo;
import com.family.memory.model.MemoryPLInfo;
import com.family.memory.model.PlHfInfo;
import com.family.memory.service.MemoryService;
import com.family.user.mapper.HomeInfoMapper;
import com.family.user.mapper.UserInfoMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author 杜飞龙
 * @date 2020年 03月20日 22:12:11
 * @jdk 1.8
 */
@Service
public class MemoryServiceImpl implements MemoryService {

    private static final Logger LOGGER= LoggerFactory.getLogger( MemoryServiceImpl.class );

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HomeInfoMapper homeInfoMapper;

    @Autowired
    private MemoryInfoMapper memoryInfoMapper;

    @Autowired
    private MemoryPLInfoMapper memoryPLInfoMapper;

    @Autowired
    private PlHfInfoMapper plHfInfoMapper;


    @Override
    public MemoryInfo addMemory(String memoryxh, String jynr, String fbrxh, MultipartFile file) {
        if(StringUtils.isNotEmpty( memoryxh )){
            return saveMemoryImg( memoryxh,file );
        }
        MemoryInfo memoryInfo=new MemoryInfo();
        memoryInfo.setJynr( jynr );
        memoryInfo.setFbrxh( fbrxh );
        memoryInfo.setXh( IdUtil.objectId() );
        memoryInfo.setCjsj( new Date() );
        String imgurl=Constants.uploadImg( file,Constants.MEMORY_IMG_PATH );
        if(StringUtils.isNotEmpty( imgurl )){
            memoryInfo.setImgurls( imgurl );
        }
        if (memoryInfoMapper.insert( memoryInfo ) < 0) {
            throw new RuntimeException( "添加记忆失败" );
        }
        return memoryInfo;
    }

    /**
     * @Description: 保存动态照片
     * @Author: 杜飞龙
     * @Date: 2020/3/28
     * @param memoryxh:
     * @param file:
     * @return: void
     **/
    public MemoryInfo saveMemoryImg(String memoryxh,MultipartFile file){
        MemoryInfo memoryInfo=memoryInfoMapper.selectByPrimaryKey( memoryxh );
        if(null==memoryInfo){
            return null;
        }
        String imgurl=Constants.uploadImg( file,Constants.MEMORY_IMG_PATH );
        if(StringUtils.isNotEmpty( imgurl )){
           String imgurls= memoryInfo.getImgurls();
           if(StringUtils.isNotEmpty( imgurls )){
               imgurls=imgurls+","+imgurl;
           }else{
               imgurls=imgurl;
           }
           memoryInfo.setImgurls( imgurls );
           if(memoryInfoMapper.updateByPrimaryKey( memoryInfo )<0){
               throw new RuntimeException( "保存图片失败" );
           }
        }
        return memoryInfo;
    }

    @Override
    public void delMemory(String xh) {
        if (memoryInfoMapper.deleteByPrimaryKey( xh ) < 0) {
            throw new RuntimeException( "删除记忆失败" );
        }
    }

    @Override
    public MemoryInfo queryMemoryByXh(String xh) {
        return memoryInfoMapper.selectByPrimaryKey( xh );
    }

    @Override
    public PageInfo<MemoryInfo> queryMemoryPageList(String homeXh, Integer pageNum, Integer pageSize) {

        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        PageHelper.startPage( pageNum, pageSize );
        List<MemoryInfo> list = memoryInfoMapper.queryMemoryPageList( homeXh );
        PageInfo<MemoryInfo> pageInfo = new PageInfo<>( list );
        return pageInfo;
    }

    @Override
    public MemoryPLInfo addMemoryPL(MemoryPLInfo memoryPLInfo) {
        memoryPLInfo.setXh( IdUtil.objectId() );
        memoryPLInfo.setCjsj( new Date() );
        if (memoryPLInfoMapper.insert( memoryPLInfo ) < 0) {
            throw new RuntimeException( "添加记忆评论失败" );
        }
        return  memoryPLInfo;
    }

    @Override
    public void delMemoryPL(String xh) {
        if (memoryPLInfoMapper.deleteByPrimaryKey( xh ) < 0) {
            throw new RuntimeException( "删除记忆评论失败" );
        }
    }

    /**
     * @param memoryXh:
     * @param pageNum:
     * @param pageSize:
     * @Description: 分页查询评论
     * @Author: 杜飞龙
     * @Date: 2020/3/26
     * @return: java.util.List<com.family.memory.model.MemoryPLInfo>
     **/
    @Override
    public PageInfo<MemoryPLInfo> queryMemoryPLPageList(String memoryXh, Integer pageNum, Integer pageSize) {
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        PageHelper.startPage( pageNum, pageSize );
        List<MemoryPLInfo> list = memoryPLInfoMapper.queryMemoryPLPageList( memoryXh );
        if(CollectionUtil.isNotEmpty( list )){
            //获取评论的回复
            list.stream().forEach( memoryPLInfo -> {
                List<PlHfInfo> hflist=plHfInfoMapper.getPlHfInfoList( memoryPLInfo.getXh() );
                if(CollectionUtil.isNotEmpty( hflist )){
                    memoryPLInfo.setReviewnum( hflist.size() );
                    memoryPLInfo.setChilddata( hflist );
                }
            });
        }
        PageInfo<MemoryPLInfo> pageInfo = new PageInfo<>( list );
        return pageInfo;
    }

    @Override
    public PlHfInfo addMemoryPLHF(PlHfInfo plHfInfo) {
        plHfInfo.setXh( IdUtil.objectId() );
        plHfInfo.setCjsj( new Date() );
        if (plHfInfoMapper.insert( plHfInfo ) < 0) {
            throw new RuntimeException( "添加记忆评论失败" );
        }
        return  plHfInfo;
    }

    /**
     * @Description: 取消动态点赞
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param memoryxh:
     * @return: void
     **/
    @Override
    public void delDz(String memoryxh,String plrxh) {
        Example example=new Example(MemoryPLInfo.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo( "memoryxh",memoryxh );
        criteria.andEqualTo( "plrxh",plrxh );
        criteria.andEqualTo( "type",Constants.PL_TYPE_1 );
        if(memoryPLInfoMapper.deleteByExample( example )<0){
            LOGGER.error( "取消点赞失败,动态序号："+memoryxh+",评论人序号："+plrxh );
            throw new RuntimeException( "取消点赞失败" );
        }

    }
}