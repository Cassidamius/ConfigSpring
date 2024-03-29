package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.common.Constant;
import com.spring.config.dao.LogsDao;
import com.spring.config.model.Logs;
import com.spring.config.pagination.PageInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.LogsService;

/**
 * 品牌
 * 
 * @author yinsl
 * 
 */
@Service("logsService")
@Transactional
public class LogsServiceImpl extends BaseServiceImpl<Logs, Integer> implements
		LogsService {

	@Autowired
	private LogsDao logsDao;
	
	public LogsServiceImpl() {
        super(Logs.class);
    }
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@DataSourceType(Constant.MASTER_DATASOURCE_KEY)
    public Integer save(Logs t) {
        return (Integer) getBaseDao().save(t);
    }

	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
	public PageResultSet<Logs> queryForPage(Logs logs, int pageSize, int page) {
		String hql = "SELECT count(id) FROM Logs b where b.deleteFlag = 1 ";
		Map<String, Object> map = new HashMap<String, Object>();
		int totalRow = logsDao.queryRowCount(
				createHql(hql, logs, map), map);
		List<Logs> logsList = null;
		PageInfo pageInfo = new PageInfo(totalRow, pageSize, page);
		if (totalRow == 0) {
			logsList = new ArrayList<Logs>();
		} else {
			hql = " FROM Brand b where b.deleteFlag = 1 ";
			map.clear();
			logsList = logsDao.queryForPage(
					createHql(hql, logs, map), map,
					pageInfo.countOffset(), pageInfo.getPageSize());
		}
		return new PageResultSet<Logs>(pageInfo, logsList);
	}

	private String createHql(String hql, Logs logs, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(hql);
		if (logs != null) {
//			if (logs.getDeleteFlag() != null) {
//				sb.append(" and b.deleteFlag = ? ");
//				paramList.add(logs.getDeleteFlag());
//			}
		}
		return sb.toString();
	}

}
