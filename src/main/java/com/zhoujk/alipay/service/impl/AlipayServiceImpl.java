package com.zhoujk.alipay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.zhoujk.alipay.config.AlipayConfig;
import com.zhoujk.alipay.entity.Course;
import com.zhoujk.alipay.entity.OrderTrade;
import com.zhoujk.alipay.mapper.CoursesMapper;
import com.zhoujk.alipay.qrcode.QRCodeResponse;
import com.zhoujk.alipay.qrcode.QRCodeUtil;
import com.zhoujk.alipay.qrcode.QRResponse;
import com.zhoujk.alipay.result.PayException;
import com.zhoujk.alipay.result.ResultCodeEnum;
import com.zhoujk.alipay.service.AlipayService;
import com.zhoujk.alipay.service.OrderTradeService;
import com.zhoujk.alipay.service.PayCommonService;
import com.zhoujk.alipay.util.GenerateNum;
import com.zhoujk.alipay.util.ParamsUtil;
import com.zhoujk.alipay.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author : zhoujiankang
 * @Desc:   支付宝支付实现
 * @since : 2022/5/3 23:16
 */


@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService
{

    Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired private AlipayConfig alipayConfig;

    @Resource private CoursesMapper coursesMapper;

    @Resource private PayCommonService payCommonService;

    @Resource private OrderTradeService orderTradeService;

    /**
     * 将请求得到二维码连接生成二维码  返回到前端
     */
    @Override
    public byte[] alipay(PayVo payVo ) throws Exception
    {

        //获取响应二维码信息
        QRCodeResponse QRCodeResponse = qrcodePay(payModel(payVo));
        //制作二维码并且返回给前端
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String logopath = "";
        logopath = ResourceUtils.getFile("classpath:favicon.jpg").getAbsolutePath();
        logger.info("二维码的图片路径为===>" + logopath);
        BufferedImage encode = QRCodeUtil.encode(QRCodeResponse.getQr_code(), logopath, false);
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        ImageIO.write(encode, "JPEG", imageOutputStream);
        imageOutputStream.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return FileCopyUtils.copyToByteArray(byteArrayInputStream);
    }


    /**
     * 支付宝客户端发送支付请求获取支付二维码信息
     */
    public QRCodeResponse qrcodePay(AlipayTradePrecreateModel model) throws AlipayApiException
    {
        //1.获取请求客户端
        AlipayClient alipayClient = getAlipayClient();

        //2. 获取请求对象
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

        //3.设置请求参数
        alipayRequest.setBizModel(model);
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());


        AlipayTradePrecreateResponse alipayResponse = alipayClient.execute(alipayRequest);
        String body = alipayResponse.getBody();
        logger.info("请求的响应二维码信息====>" + body);
        QRResponse QRResponse = JSON.parseObject(body, QRResponse.class);
        return QRResponse.getAlipay_trade_precreate_response();
    }

    /**
     * 获取阿里客户端
     *
     * @return
     */
    public AlipayClient getAlipayClient()
    {
        DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(alipayConfig.getURL(), alipayConfig.getAPPID(), alipayConfig.getRSA_PRIVATE_KEY(), alipayConfig.getFORMAT(), alipayConfig.getCHARSET(), alipayConfig.getALIPAY_PUBLIC_KEY(), alipayConfig.getSIGNTYPE());
        return defaultAlipayClient;
    }


    /**
     *   设置支付参数
     * @param payVo
     * @return
     */
    public AlipayTradePrecreateModel payModel(PayVo payVo)
    {
        //获取课程商品
        Course courses = coursesMapper.selectById(payVo.getCourseId());
        if (courses == null) {
            throw new PayException(ResultCodeEnum.SYSTEM_EXCEPTION);
        }
        String orderNumber = GenerateNum.generateOrder();

        //设置支付回调时可以在request中获取的参数(msg)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courses.getCourseid());
        jsonObject.put("courseTitle", courses.getTitle());
        jsonObject.put("courseImg", courses.getImg());
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("payType", payVo.getPayMethod());
        jsonObject.put("price", courses.getPrice());
        String params = jsonObject.toString();

        //设置支付参数
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setBody(params);
        model.setTotalAmount(courses.getPrice().toString());
        model.setOutTradeNo(orderNumber);
        model.setSubject(courses.getTitle());

        return model;
    }


    /**
     * 支付回调
     */
    @Override
    public Boolean alipayCallback(HttpServletRequest request)
    {
        try {
            Map<String, String> params = ParamsUtil.ParamstoMap(request);
            logger.info("回调参数=========>" + params);
            String trade_no = params.get("trade_no");
            String body1 = params.get("body");
            logger.info("交易的流水号和交易信息===========>", trade_no, body1);
            JSONObject body = JSONObject.parseObject(body1);
            String ptype = body.getString("payType");
            String orderNumber = body.getString("orderNumber");
            if (ptype != null && ptype.equals("1")) {
                payCommonService.payproductcourse(body, "1", orderNumber, trade_no, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常====>", e.toString());
            return false;
        }
        return true;
    }

    /**
     * 申请退款
     *
     * @param orderTrade 退款VO
     * @return
     * @throws AlipayApiException 支付宝Api异常
     */
    @Override
    public boolean refund(OrderTrade orderTrade)

    {
        //1.获取请求客户端
        AlipayClient alipayClient = getAlipayClient();

        //建立退款请求
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        JSONObject bizContent = new JSONObject();


        //建立请求参数集合
        bizContent.put("out_trade_no", orderTrade.getOutTradeNO());
        bizContent.put("trade_no", orderTrade.getTradeNo());
        bizContent.put("refund_amount", orderTrade.getTotalAmount());

        //返回参数选项
        JSONArray queryOptions = new JSONArray();
        queryOptions.add("refund_detail_item_list");
        bizContent.put("query_options", queryOptions);


        try {
            alipayRequest.setBizContent(bizContent.toString());
            AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
            if (alipayResponse.isSuccess()) {
                logger.info("退款成功");
                return true;
            } else {
                logger.info("退款失败");
                return false;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info("出现异常，退款失败！");
            return false;
        }

    }

    /**
     * 获取支付宝交易号
     *
     * @param out_trade_no
     * @return
     */

    @Override
    public String query(String out_trade_no)
    {
        //1.获取请求客户端
        AlipayClient alipayClient = getAlipayClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        JSONObject bizContent = new JSONObject();

        bizContent.put("out_trade_no", out_trade_no);
        request.setBizContent(bizContent.toString());

        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response.getTradeNo();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info("查询失败");
        }
        return "";

    }

}


