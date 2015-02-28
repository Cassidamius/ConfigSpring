package com.spring.config.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	@DataSourceType(Constant.SLAVE_DATASOURCE_KEY)
    public PageResultSet<Resource> queryForPage(Resource condition, int pageSize, int currentPage) {
		String hql = "SELECT count(id) FROM Resource r where 1 = 1 ";
        List<Object> objList = new ArrayList<Object>();
        int totalRow = resourceDao.queryRowCount(createHql(hql, condition, objList), objList);
        List<Resource> resources = null;
        PageInfo pageInfo = new PageInfo(totalRow, pageSize, currentPage);
        if (totalRow == 0) {
            resources = new ArrayList<Resource>();
        } else {
            hql = "FROM Resource r where 1 = 1 ";
            objList.clear();
            resources = resourceDao.queryForPage(createHql(hql, condition, objList), objList, pageInfo.countOffset(),
                    pageInfo.getPageSize());
        }

        return new PageResultSet<Resource>(pageInfo, resources);
    }

    private String createHql(String hql, Resource resource, List<Object> objList) {
        StringBuffer sb = new StringBuffer(hql);
        if (resource != null) {
            if (StringUtils.isNotEmpty(resource.getName())) {
                sb.append(" and r.name like ? ");
                objList.add("%" + resource.getName() + "%");
            }
            if (StringUtils.isNotEmpty(resource.getRescString())) {
                sb.append(" and r.rescString like ? ");
                objList.add("%" + resource.getRescString() + "%");
            }
        }
        return sb.toString();
    }

}
