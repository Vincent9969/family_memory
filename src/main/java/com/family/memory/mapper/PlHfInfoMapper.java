package com.family.memory.mapper;

import com.family.memory.model.PlHfInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PlHfInfoMapper extends Mapper<PlHfInfo> {

    List<PlHfInfo> getPlHfInfoList(@Param( "plxh" ) String plxh);
}