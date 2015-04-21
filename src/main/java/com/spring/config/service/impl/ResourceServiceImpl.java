package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.config.annotation.DataSourceType;
import com.spring.config.common.Constant;
import com.spring.config.dao.ResourceDao;
import com.spring.config.model.Resource;
import com.spring.config.pagination.PageInfo;
import com.spring.config.pagination.PageResultSet;
import com.spring.config.service.ResourceService;

@Service("resourceService")
@Transactional
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	public ResourceServiceImpl() {
		super(Resource.class);
	}

	@DataSourceType(Constant.MASTER_DATASOURCE_KEY)
	public Integer updateResource(Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "update Resource t set t.name = :name, t.rescType = :rescType, t.rescString = :rescString, "
		        + " t.version = t.version + 1, t.updateTime = current_timestamp "
		        + " where t.id = :id and t.version = :version";
		map.put("name", resource.getName());
		map.put("rescType", resource.getRescType());
		map.put("rescString", resource.getRescString());
		map.put("id", resource.getId());
		map.put("version", resource.getVersion());
		return resourceDao.update(hql, map);
	}

	@Override
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
	public PageResultSet<Resource> queryForPage(Resource condition, int pageSize, int currentPage) {
		String hql = "SELECT count(id) FROM Resource r where r.deleteFlag = 1 ";
		Map<String, Object> map = new HashMap<String, Object>();
		int totalRow = resourceDao.queryRowCount(createHql(hql, condition, map), map);
		List<Resource> resources = null;
		PageInfo pageInfo = new PageInfo(totalRow, pageSize, currentPage);
		if (totalRow == 0) {
			resources = new ArrayList<Resource>();
		} else {
			hql = "FROM Resource r where deleteFlag = 1 ";
			map.clear();
			resources = resourceDao.queryForPage(createHql(hql, condition, map), map, pageInfo.countOffset(),
			        pageInfo.getPageSize());
		}

		return new PageResultSet<Resource>(pageInfo, resources);
	}

	private String createHql(String hql, Resource resource, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer(hql);
		if (resource != null) {
			if (StringUtils.isNotEmpty(resource.getName())) {
				sb.append(" and r.name like :name ");
				map.put("name", "%" + resource.getName() + "%");
			}
			if (StringUtils.isNotEmpty(resource.getRescString())) {
				sb.append(" and r.rescString like :rescString ");
				map.put("rescString", "%" + resource.getRescString() + "%");
			}
		}
		return sb.toString();
	}

}
