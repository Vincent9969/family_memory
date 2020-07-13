package com.family.memory.mapper;

import com.family.memory.model.MemoryPLInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemoryPLInfoMapper extends Mapper<MemoryPLInfo> {

    List<MemoryPLInfo> queryMemoryPLPageList(@Param( "memoryXh" ) String memoryXh);
}