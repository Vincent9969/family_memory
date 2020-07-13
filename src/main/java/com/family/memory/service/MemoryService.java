package com.family.memory.service;

import com.family.memory.model.MemoryInfo;
import com.family.memory.model.MemoryPLInfo;
import com.family.memory.model.PlHfInfo;
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
public interface MemoryService {

    public MemoryInfo addMemory(String memoryxh, String jynr, String fbrxh, MultipartFile file);

    public void delMemory(String xh);

    public MemoryInfo queryMemoryByXh(String xh);

    /**
     * @Description: 分页查询回忆记录
     * @Author: 杜飞龙
     * @Date: 2020/3/26

     * @return: java.util.List<com.family.memory.model.MemoryInfo>
     **/
    public PageInfo<MemoryInfo> queryMemoryPageList(String homeXh, Integer pageNum, Integer pageSize);

    /**
     * @Description: 添加评论和点赞
     * @Author: 杜飞龙
     * @Date: 2020/3/26
     * @param memoryPLInfo:
     * @return: void
     **/
    public MemoryPLInfo addMemoryPL(MemoryPLInfo memoryPLInfo);

    /**
     * @Description: 删除评论 或 取消点赞
     * @Author: 杜飞龙
     * @Date: 2020/3/26
     * @param xh:
     * @return: void
     **/
    public void delMemoryPL(String xh);


    /**
     * @Description: 分页查询回忆记录
     * @Author: 杜飞龙
     * @Date: 2020/3/26

     * @return: java.util.List<com.family.memory.model.MemoryInfo>
     **/
    public PageInfo<MemoryPLInfo> queryMemoryPLPageList(String memoryXh,Integer pageNum,Integer pageSize);



    /**
     * @Description: 添加评论回复
     * @Author: 杜飞龙
     * @Date: 2020/3/28
     * @param plHfInfo:
     * @return: java.lang.String
     **/
    PlHfInfo addMemoryPLHF(PlHfInfo plHfInfo);

    void delDz(String memoryxh,String plr);
}