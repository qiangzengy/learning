package com.qiangzeng.learning.eduservice.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.qiangzeng.learning.common.util.ResponseResult;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午1:04
 */

@Component
public class OssUtils implements InitializingBean {

    @Value("${END_POIND}")
    private String endpoint;

    @Value("${ACCESS_KEY_ID}")
    private String accessKeyId;

    @Value("${ACCESS_KEY_SECRET}")
    private String accessKeySecret;

    @Value("${BUCKET_NAME}")
    private String bucketName;

    private OSS  ossClient;

    //定义公开静态常量
    public static String END_POIND;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }

    //上传头像到oss
    public String uploadFile(MultipartFile file) {

        try {
            // 创建OSS实例。
            ossClient = new OSSClientBuilder().build(END_POIND, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            fileName = datePath + "/" + fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称
            //第三个参数  上传文件输入流
            ossClient.putObject(BUCKET_NAME, fileName, inputStream);

            //加签名处理
            // 指定过期时间为1年。
            Date expiration = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 365 );
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(BUCKET_NAME, fileName);
            req.setExpiration(expiration);
            URL signedUrl = ossClient.generatePresignedUrl(req);
            return signedUrl.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }




}
