

package com.cloud.manager.api.service;

import com.cloud.manager.compensate.model.TxModel;
import com.cloud.manager.model.ModelName;
import com.cloud.manager.model.TxState;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author LCN on 2017/11/12
 * @author ygnet
 */
public interface ApiAdminService {

	TxState getState();

	/**
	 * k/v 获取 值封装成map
	 *
	 * @return
	 */
	List<Map<String, Object>> getMapState();

	String loadNotifyJson();

	List<ModelName> modelList();


	List<String> modelTimes(String model);

	List<TxModel> modelInfos(String path);

	boolean compensate(String path) throws ServiceException;

	boolean hasCompensate();

	boolean delCompensate(String path);
}
