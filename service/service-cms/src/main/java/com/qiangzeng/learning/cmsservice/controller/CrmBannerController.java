package com.qiangzeng.learning.cmsservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.cmsservice.entity.CrmBanner;
import com.qiangzeng.learning.cmsservice.service.CrmBannerService;
import com.qiangzeng.learning.common.util.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author qiangzeng
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/eduservice/crm-banner")
public class CrmBannerController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public ResponseResult pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
       IPage<CrmBanner> pages =bannerService.page(pageBanner,null);
        return ResponseResult.success(pages);
    }

    //2 添加banner
    @PostMapping("addBanner")
    public ResponseResult addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return ResponseResult.success();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return ResponseResult.success(banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResponseResult updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable String id) {
        bannerService.removeById(id);
        return ResponseResult.success();
    }


}

