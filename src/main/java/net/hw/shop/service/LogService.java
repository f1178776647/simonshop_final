package net.hw.shop.service;

import net.hw.shop.bean.Log;
import net.hw.shop.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by howard on 2017/5/7.
 */
@Service("logService")
@Transactional(readOnly = true)
public class LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 保存日志
     *
     * @param log
     * @return
     */
    @Transactional(readOnly = false)
    public int save(Log log) {
        return logMapper.insert(log);
    }

    /**
     * 按标识符删除日志
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(int id) {
        return logMapper.delete(id);
    }

    /**
     * 按标识符查询日志
     *
     * @param id
     * @return
     */
    public Log findById(Integer id) {
        return logMapper.findById(id);
    }

    /**查询日志列表
     *
     * @param log
     * @return
     */
    public List<Log> findList(Log log) {
        return logMapper.findList(log);
    }
}
