package com.spring.config.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.config.dao.LogsDao;
import com.spring.config.model.Logs;

/**
 * 日志
 * @author yinsl
 *
 */
@Repository
public class LogsDaoImpl extends BaseDaoImpl<Logs, Integer> implements LogsDao {

    public LogsDaoImpl() {
        super(Logs.class);
    }

}
