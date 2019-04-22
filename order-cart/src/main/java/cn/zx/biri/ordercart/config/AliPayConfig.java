package cn.zx.biri.ordercart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:45 2019/4/14 0014
 */
@ConfigurationProperties("alipay")
public class AliPayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    private String appID;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String merchantPrivateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 签名方式
     */
    private String signType="RSA2";

    /**
     * 网关
     */
    private String gatewayUrl="https://openapi.alipay.com/gateway.do";

    /**
     * 编码
     */
    private String  charset= "utf-8";

    /**
     * 异步通知地址
     */
    private String notifyUrl="";

    /**
     * 类型
     */
    private String format="json";

    /**
     * 商户号
     */
    private String  sysServiceProviderId="1234";

}
