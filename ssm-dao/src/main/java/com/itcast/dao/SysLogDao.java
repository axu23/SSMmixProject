package com.itcast.dao;


import com.itcast.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface SysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;
}
