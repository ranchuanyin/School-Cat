package com.ranchuanyin.schoolcat.units;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Component
public class OSSUnit {
    private static final Logger LOG = LoggerFactory.getLogger(OSSUnit.class);
    @Value("${OSS.AK}")
    private String AK;
    @Value("${OSS.SK}")
    private String SK;
    @Value("${OSS.bucket}")
    private String bucket;
    @Value("${OSS.url}")
    private String url;

    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String filename = PathUnit.getFilePath(originalFilename);
            return uploadImages(file, filename);
        }
        return null;
    }

    private String uploadImages(MultipartFile multipartFile, String fileName) {
        Configuration cfg = new Configuration(Region.xinjiapo());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Auth auth = Auth.create(AK, SK);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return url + putRet.key;
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);
                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (UnsupportedEncodingException ex) {
            LOG.info(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
