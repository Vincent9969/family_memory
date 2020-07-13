package com.family.memory.mapper;

import com.family.memory.model.MemoryInfo;
import com.family.memory.model.MemoryPLInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemoryInfoMapper extends Mapper<MemoryInfo> {

    public List<MemoryInfo> queryMemoryPageList(@Param( "homeXh" ) String homeXh);
}