package com.itecheasy.test;

import com.itecheasy.common.PageList;
import com.itecheasy.core.fba.AmazonReportService;
import com.itecheasy.core.order.AmazonStockReport;
import com.itecheasy.core.order.AmazonStockReportSearchForm;
import com.itecheasy.core.order.OrderTrackingMessageService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @Auther: liteng
 * @Date: 2018/7/5 14:55
 * @Description:
 */
public class AmazonReportServiceImplTest {

    @Test
    public void getAmazonStocks() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AmazonReportService amazonReportService = (AmazonReportService) context.getBean("amazonReportService");


        AmazonStockReportSearchForm form = new AmazonStockReportSearchForm();
        form.setOperatorId(1);
        form.setShopId(10);
        form.setSku("chino003");

        form.setCurrentPage(1);
        form.setPageSize(5);
        PageList<AmazonStockReport> amazonStocks = amazonReportService.getAmazonStocks(form);
    }
}