

package com.cloud.manager.compensate.service;

import com.lorne.core.framework.exception.ServiceException;
import com.cloud.manager.compensate.model.TransactionCompensateMsg;
import com.cloud.manager.compensate.model.TxModel;
import com.cloud.manager.model.ModelName;
import com.cloud.manager.netty.model.TxGroup;

import java.util.List;

/**
 * @author LCN on 2017/11/11
 */
public interface CompensateService {

	boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<ModelName> loadModelList();

	List<String> loadCompensateTimes(String model);

	List<TxModel> loadCompensateByModelAndTime(String path);

	void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);

	boolean executeCompensate(String key) throws ServiceException;

	void reloadCompensate(TxGroup txGroup);

	boolean hasCompensate();

	boolean delCompensate(String path);

	TxGroup getCompensateByGroupId(String groupId);
}
