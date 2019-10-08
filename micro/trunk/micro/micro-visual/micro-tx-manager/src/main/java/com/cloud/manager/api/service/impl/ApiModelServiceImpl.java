

package com.cloud.manager.api.service.impl;

import com.cloud.manager.manager.ModelInfoManager;
import com.cloud.manager.model.ModelInfo;
import com.cloud.manager.api.service.ApiModelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LCN on 2017/11/13
 * @author LCN
 */
@Service
public class ApiModelServiceImpl implements ApiModelService {


	@Override
	public List<ModelInfo> onlines() {
		return ModelInfoManager.getInstance().getOnlines();
	}


}
