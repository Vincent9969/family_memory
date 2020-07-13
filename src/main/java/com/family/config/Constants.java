package com.family.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 杜飞龙
 * @date 2020年 01月19日 12:14:37
 * @jdk 1.8
 */
public class Constants {

    private static final Logger LOGGER= LoggerFactory.getLogger( Constants.class );

    public static final String FILE_TYPE_JPG="jpg";

    public static  final String HTTP_STATUS_SUCCESS="success";

    public static  final String HTTP_STATUS_FAILED="failed";

    public static  final String HTTP_DATA_STATUS_NAME="status";

    public static  final String HTTP_STATUS_ERROR="error";

    public static final  String HOME_NAME_DEFULT="我的家";

    public static final  String HOME_DESC_DEFULT="温馨的小家";

    public static final  int ADD_HOURS=1;

    public static String getSystemMemoryContent(String nickName){
        String content=nickName+"欢迎来到温馨的小家,在这里你可以邀请你的家人一起记录你们美好的生活！" +
                " 在这里所有的信息都只对家人可见的哦！快去邀请你的家人一起记录你们幸福的足迹。";
        return content;
    }

    public static  final String PL_HF_TYPE_1="1";

    public static  final String PL_HF_TYPE_0="0";

    public static  final String GET_IMG_URL = "http://192.168.1.3:8089/showImage/";

    public static final String [] setImgUrl(String [] imgs){
       for(int i=0;i<imgs.length;i++){
           imgs[i]=GET_IMG_URL+imgs[i];
       }
       return imgs;
    }

    public static final String  setImgUrl(String  img){
        return GET_IMG_URL+img;
    }


    /*
     * @Description:点赞
     */
    public static  final String PL_TYPE_1="1";


    /*
     * @Description:评论
     */
    public static  final String PL_TYPE_2="2";


    /*
     * @Description: 动态相片保存位置
     */
    public static  final String MEMORY_IMG_PATH="D://";


    /*
     * @Description:相册图片保存位置
     */
    public static  final String PHOTO_IMG_PATH="D://";


    public static String uploadImg(MultipartFile multipartFile,String path){
        return uploadImg(multipartFile,path,null);
    }

    /**
     * @Description: 上传图片
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param multipartFile:
     * @param path:
     * @return: java.lang.String
     **/
    public static String uploadImg(MultipartFile multipartFile,String path,String fileName){
        //创建文件夹
//        createFolder( path );
        String fileSavePath="D://";
        if (null == multipartFile || multipartFile.getSize() <= 0) {
            return null;
        }
        //文件名
        String originalName = multipartFile.getOriginalFilename();
        if(StringUtils.isEmpty( fileName )) {
            fileName = IdUtil.objectId();
        }
        String houzui =  originalName.substring(originalName.lastIndexOf(".")+1);
        String imgRealPath = fileSavePath + fileName+"."+houzui;
        BufferedImage bufferedImage;
        try {
            if(Constants.FILE_TYPE_JPG.equals( houzui.toLowerCase() )){
                File imageFile=new File(imgRealPath);
                multipartFile.transferTo(imageFile);
            }else{
                //保存图片-将multipartFile对象装入image文件中
                File imageFile= MultipartFileToFile.multipartFileToFile( multipartFile );
                bufferedImage = ImageIO.read(imageFile);
                BufferedImage newBufferedImage = new BufferedImage(
                        bufferedImage.getWidth(), bufferedImage.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
                newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
                        Color.WHITE, null);
                // write to jpeg file
                ImageIO.write(newBufferedImage, Constants.FILE_TYPE_JPG, new File(fileSavePath + fileName+"."+Constants.FILE_TYPE_JPG));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  fileName+"."+houzui;
    }

    /**
     * 删除文件夹
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath){
        boolean result = false;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            result = true;
            LOGGER.info("文件已经被成功删除,文件名称:"+filePath);
        }
        return result;
    }

    /**
     * 删除文件批量
     *
     * @param filePath
     * @return
     */
    public static void deleteFile(String basePath,String[] filePath){
        if(null==filePath || filePath.length<1){
            return;
        }
        for(int i=0;i<filePath.length;i++){
            deleteFile( basePath+"/"+filePath[i] );
        }
    }

    public static void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
    }

}