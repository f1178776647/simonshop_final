package net.hw.shop.mapper;

import net.hw.shop.bean.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Mapper
public interface LogMapper {
    // 插入日志
    int insert(Log log);

    // 按标识符删除日志
    int delete(Integer id);

    // 按标识符查询日志
    Log findById(Integer id);

    // 查询日志列表
    List<Log> findList(Log log);
}
