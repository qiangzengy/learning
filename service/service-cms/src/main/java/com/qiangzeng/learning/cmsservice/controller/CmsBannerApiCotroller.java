package com.qiangzeng.learning.cmsservice.controller;

import com.qiangzeng.learning.cmsservice.entity.CrmBanner;
import com.qiangzeng.learning.cmsservice.service.CrmBannerService;
import com.qiangzeng.learning.common.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/8 下午12:36
 */


@RestController
@RequestMapping("/cmsservice/crm-banner")
public class CmsBannerApiCotroller {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public ResponseResult getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return ResponseResult.success(list);
    }
}
