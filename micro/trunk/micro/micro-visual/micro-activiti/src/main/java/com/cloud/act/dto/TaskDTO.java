

package com.cloud.act.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ygnet
 * @date 2018/9/25
 */
@Data
public class TaskDTO {
	private String applicant;
	private String taskId;
	private String taskName;
	private String title;
	private String pdName;
	private String version;
	private Date time;
	private String processInstanceId;
	private String status;
	private String nodeKey;
	private String processDefKey;
	private String category;
}
