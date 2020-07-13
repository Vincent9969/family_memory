package com.family.img.mapper;

import com.family.img.model.ImgGroupInfo;
import com.family.memory.model.MemoryPLInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImgGroupInfoMapper extends Mapper<ImgGroupInfo> {
    List<ImgGroupInfo> queryImgGroupPageList(@Param( "homexh" ) String homexh);
}