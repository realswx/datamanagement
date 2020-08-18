package com.dm.mapper;

import com.dm.pojo.Log;

import java.util.List;

public interface LogMapper {

    //保存日志
    int save(Log log);

    /**
     * 通过日志类型查询
     * @param logType
     * @return
     */
    List<Log> queryAllByLogType(String logType);


    int deleteByPrimaryKey(Integer logId);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);
}