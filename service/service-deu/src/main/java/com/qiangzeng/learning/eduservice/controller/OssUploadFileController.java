package com.qiangzeng.learning.eduservice.controller;

import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.utils.OssUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午1:39
 */

@RestController
@RequestMapping("/eduservice/oss-upload")
public class OssUploadFileController {


    @ApiOperation(value = "文件上传OSS")
    @RequestMapping(value ="fileUpload" ,method = RequestMethod.POST)
    public ResponseResult fileUpload(MultipartFile file){

        OssUtils ossUtils=new OssUtils();
        try{
            String url=ossUtils.uploadFile(file);
            return ResponseResult.success(url);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.error("上传失败");
        }
    }


}
