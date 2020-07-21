package com.changgou.file.test;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/19 - 2:47 下午 - 星期日
 * @Package : com.changgou.file.test
 * @ProjectName : changgou
 */
public class FastdfsClientTest {
    //初始化配置信息

    static {
        ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
        String path = classPathResource.getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();


        return connection;
    }

    //测试上传功能
    @Test
    public void uploadTest() throws IOException, MyException {
        //因为只有一个storageServer 所以在创建需要时我均使用默认的，传了null
        StorageClient storageClient = new StorageClient(null,null);
        String filename = "/Users/yuzebo/Downloads/testimage.jpg";
        String extname = StringUtils.getFilenameExtension(filename);
        String[] msg = storageClient.upload_file(filename,extname,null);
        String group = msg[0];
        String filepath = msg[1];

        TrackerServer connection = getTrackerServer();
        String hostName = connection.getInetSocketAddress().getHostName();
        int port = ClientGlobal.getG_tracker_http_port();

        String url = "http://" + hostName + ":" + port + "/" + group + "/" + filepath;
        System.out.println(url);
    }

    //测删除图片
    @Test
    public void delete() throws Exception {
        //TrackerClient trackerClient = new TrackerClient();
        //TrackerServer connection = trackerClient.getConnection();

        //如果不传入connection 则默认找 server[0]
        //因为只有一个storageServer 所以在创建需要时我均使用默认的，传了null
        StorageClient storageClient = new StorageClient(null,null);

        int group1 = storageClient.delete_file("group1", "M00/00/00/wKgAb18UEF2AEbSGAAX3Qzefdxg922.jpg");
    }

    //测试下载图片 因为只有一个storageServer 所以在创建需要时我均使用默认的，传了null
    @Test
    public void downloadTest() throws IOException, MyException {
        //wKgAb18UEF2AEbSGAAX3Qzefdxg922.jpg
        StorageClient storageClient = new StorageClient();
        byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKgAb18UEF2AEbSGAAX3Qzefdxg922.jpg");

        File file = new File("/Users/yuzebo/Downloads/test/test_download_image1.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        bufferedOutputStream.write(bytes);

        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    //获取组相关的信息
    @Test
    public void getGroupInfo() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        StorageServer StorageServer = trackerClient.getStoreStorage(null, "group1");
        if (StorageServer != null) {
            int storePathIndex = StorageServer.getStorePathIndex();
            System.out.println("storePathIndex = " + storePathIndex);
        }

        ServerInfo[] group1s = trackerClient.getFetchStorages(null, "group1", "M00/00/00/wKgAb18UEF2AEbSGAAX3Qzefdxg922.jpg");
        for (ServerInfo group : group1s) {
            String ipAddr = group.getIpAddr();
            System.out.println("ipAddr = " + ipAddr);
        }
    }

    //获取trackerserver的信息
    @Test
    public void getTrackerInfo() throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        InetSocketAddress inetSocketAddress = trackerServer.getInetSocketAddress();
        System.out.println("inetSocketAddress = " + inetSocketAddress);
    }
}
