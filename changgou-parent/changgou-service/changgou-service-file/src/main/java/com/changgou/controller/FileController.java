package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/16 - 10:07 下午 - 星期四
 * @Package : com.changgou.controller
 * @ProjectName : changgou
 */

@RestController
@CrossOrigin
public class FileController {

    @PostMapping("/upload")
    public Result upload(@RequestParam("filename") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String ext = StringUtils.getFilenameExtension(filename);

        FastDFSFile fastDFSFile = new FastDFSFile(filename,file.getBytes(),ext);
        String[] upload = FastDFSClient.upload(fastDFSFile);
        String url = FastDFSClient.getTrackerUrl()+"/"+upload[0]+"/"+upload[1];
        return  new Result(true, StatusCode.OK,"上传成功",url);
    }
}
