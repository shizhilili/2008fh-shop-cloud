package com.fh.shop.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OssUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static String accessKeyId = "LTAI5tCiBQhBUQ4gyrzmP68X";
    private static String accessKeySecret = "HjkxUqv1ntjqtaia39jAKUNxzf8xxP";
    private static String bucketName="cyr0909";
    private static String url="https://cyr0909.oss-cn-beijing.aliyuncs.com/";

    public static void deleteFile(String filePath){
        String objectName = filePath.replace(url, "");
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(bucketName, objectName);
        } finally {
            if (ossClient !=null){
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static void deleteFiles(List<String> filePaths){
        List<String> imagePaths = filePaths.stream().map(x -> x.replace(url, "")).collect(Collectors.toList());
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(imagePaths));
        } finally {
            if (ossClient !=null){
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static String upload(String fileName,InputStream inputStream){


        OSS ossClient = null;
        String res;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + com.fh.shop.util.FileUtil.getSuffix(fileName);
            //每天上传的图片存到按日期定义名字放入一个文件夹里面
            String datePath = com.fh.shop.util.DateUtil.date2str(new Date(), com.fh.shop.util.DateUtil.Y_M_D);
            String objectName=datePath+"/"+newFileName;
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName,objectName, inputStream);
            //输出图片路径
            res=url+objectName;
            return res;
        } finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ossClient !=null){
                ossClient.shutdown();
            }
        }

    }


}
