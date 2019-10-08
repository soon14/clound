

package com.cloud.manager.manager.service;

import com.cloud.manager.model.TxServer;
import com.cloud.manager.model.TxState;

/**
 * @author LCN on 2017/11/11
 */
public interface MicroService {

	String TMKEY = "tx-manager";

	TxServer getServer();

	TxState getState();
}
