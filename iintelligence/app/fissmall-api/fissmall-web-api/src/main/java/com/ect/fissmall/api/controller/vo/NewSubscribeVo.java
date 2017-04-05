package com.ect.fissmall.api.controller.vo;

import com.ect.common.error.Result;
import com.ect.common.lang.util.StringUtil;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by liyou on 2016/10/18.
 */
public class NewSubscribeVo {
    @ApiModelProperty(value = "用户ID", required = true)
    private String     userId;
    @ApiModelProperty(value = "银行卡账号", required = true)
    private String     bankAccount;
    @ApiModelProperty(value = "认购金额", required = true)
    private BigDecimal moneyAmount;
    @ApiModelProperty(value = "产品ID", required = true)
    private String     productNum;
    @ApiModelProperty(value = "产品名称", required = true)
    private String     productName;
    @ApiModelProperty(value = "产品编码")
    private String     productCode;
    @ApiModelProperty(value = "产品类型", required = true)
    private String     productType;
    @ApiModelProperty(value = "支付密码", required = true)
    private String     payPassword;
    @ApiModelProperty(value = "AES解密随机因子", required = true)
    private String     randomFactor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
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

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getRandomFactor() {
        return randomFactor;
    }

    public void setRandomFactor(String randomFactor) {
        this.randomFactor = randomFactor;
    }

    public Result<String> isIllegalObject() {
        Result<String> result = new Result<>(true,null,null);
        StringBuilder sb = new StringBuilder();
        if (StringUtil.isEmpty(userId)){
            result.setSuccess(false);
            sb.append("用户ID为空").append(",");
        }
        if(StringUtil.isEmpty(bankAccount)){
            result.setSuccess(false);
            sb.append("银行卡号为空").append(",");
        }
        if(moneyAmount == null || moneyAmount.compareTo(BigDecimal.ZERO) < 1){
            result.setSuccess(false);
            sb.append("交易金额无效 : ").append(moneyAmount).append(",");
        }
        if(StringUtil.isEmpty(productNum)){
            result.setSuccess(false);
            sb.append("产品ID为空").append(",");
        }
        if(StringUtil.isEmpty(productName)){
            result.setSuccess(false);
            sb.append("产品名为空").append(",");
        }
        if(StringUtil.isEmpty(productType)){
            result.setSuccess(false);
            sb.append("产品为空").append(",");
        }
        if(StringUtil.isEmpty(payPassword) || StringUtil.isEmpty(randomFactor)){
            result.setSuccess(false);
            sb.append("支付密码为空").append(",");
        }
        if(sb.length()>0) {
            sb.deleteCharAt(sb.length() - 1);
            result.setResultObj(sb.toString());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("userId[").append(userId).append("],");
        sb.append("bankAccount[").append(bankAccount).append("],");
        sb.append("moneyAmount[").append(moneyAmount).append("],");
        sb.append("productNum[").append(productNum).append("],");
        sb.append("productName[").append(productName).append("],");
        sb.append("productCode[").append(productCode).append("],");
        sb.append("productType[").append(productType).append("],");
        sb.append("payPassword[").append(payPassword).append("],");
        sb.append("randomFactor[").append(randomFactor).append("].");
        return sb.toString();
    }
}
