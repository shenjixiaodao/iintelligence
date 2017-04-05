/**
 * 
 */
package com.ii.iintelligence.api.controller.result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class WebResult {
	//1表示成功，非1表示失败
	 @ApiModelProperty(position=1,required = true, value="1表示成功，非1表示失败")
	private String resultCode;
	//消息描述
	 @ApiModelProperty(position=2,value="消息描述")
	private String message;
	@ApiModelProperty(position=3,value="错误码")
	private String errorCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
