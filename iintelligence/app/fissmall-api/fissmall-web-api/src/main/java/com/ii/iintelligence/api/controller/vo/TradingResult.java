package com.ii.iintelligence.api.controller.vo;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradingResult extends WebResult implements Serializable {
    @ApiModelProperty(value = "交易流水号")
    private String            tradingId;

    @ApiModelProperty(value = "用户ID")
    private String            userId;

    @ApiModelProperty(value = "交易账户ID")
    private String            accountId;

    @ApiModelProperty(value = "产品ID")
    private String productNum;

    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value="产品类型")
    private String productType;

    // 交易类型 [SUBSCRIBE|BUY|REDEEM]
    @ApiModelProperty(value="交易类型")
    private String            type;
    // 交易状态
    // [CREATED|INVALID|WAITPAY|PAYUNKNOWN|NEEDCONFIRM|PAYFAILED|CANCELLED|SUCCEED|FAILED]
    @ApiModelProperty(value="交易状态[CREATED(\"已生成\"),INVALID(\"已失效\"),WAITPAY(\"待支付\")," +
            "PAYUNKNOWN(\"支付未明\"),NEEDCONFIRM(\"申请中\"),PAYFAILED(\"扣款失败\")," +
            "CANCELLED(\"已撤单\"),SUCCEED(\"申请成功\"),FAILED(\"申请失败\")]")
    private String            status;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="交易日期")
    private Date              applyTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="交易确认日期")
    private Date              succeedTime;

    @ApiModelProperty(value="交易金额")
    private BigDecimal             moneyAmount;

    @ApiModelProperty(value="产品份额")
    private BigDecimal        shareAmount;

    @ApiModelProperty(value="确认金额")
    private BigDecimal             confirmedMoney;

    @ApiModelProperty(value="确认产品份额")
    private BigDecimal        confirmedShare;

    public String getTradingId() {
        return tradingId;
    }

    public void setTradingId(String tradingId) {
        this.tradingId = tradingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getSucceedTime() {
        return succeedTime;
    }

    public void setSucceedTime(Date succeedTime) {
        this.succeedTime = succeedTime;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    public BigDecimal getConfirmedMoney() {
        return confirmedMoney;
    }

    public void setConfirmedMoney(BigDecimal confirmedMoney) {
        this.confirmedMoney = confirmedMoney;
    }

    public BigDecimal getConfirmedShare() {
        return confirmedShare;
    }

    public void setConfirmedShare(BigDecimal confirmedShare) {
        this.confirmedShare = confirmedShare;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
