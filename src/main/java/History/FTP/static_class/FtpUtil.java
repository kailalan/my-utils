package History.FTP.static_class;

import java.io.*;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;

/**
 * ftp连接工具类
 * @author zhangqh
 * @date 2018-6-16 上午09:41:31
 */
public class FtpUtil {

    /**
     * 日志
     * @author zhangqh
     * @date 2016-7-16 上午10:07:04
     */
    private static Logger logger = Logger.getLogger(FtpUtil.class);
    /**
     * ftp用户名
     * @author zhangqh
     * @date 2016-7-16 上午09:41:55
     */
    private static String username = "ftpuser";

    /**
     * ftp用户密码
     * @author zhangqh
     * @date 2016-7-16 上午09:42:07
     */
    private static String password = "YXftP#2016";

    /**
     * 登录地址
     * @author zhangqh
     * @date 2016-7-16 上午09:45:37
     */
    private static String hostname = "10.1.128.213";
    /**
     * 连接端口号
     * @author zhangqh
     * @date 2016-7-16 上午09:45:51
     */
    private static String port = "21";


    /**
     * ftp连接对象
     * @author zhangqh
     * @date 2016-7-16 上午10:09:17
     */
    private final static FTPClient ftpClient = new FTPClient();


    /**
     * byte[]转Base64String
     * @param bytes
     * @return String
     * @author zhangqh
     * @date 2016-7-16 上午09:47:57
     */
    public static String encodeBase64String(byte[] bytes){
        logger.debug("【History.FTP.FtpUtil】byte[]转Base64String");
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base64String 转byte[]
     * @param base64String
     * @return  byte[]
     * @author zhangqh
     * @date 2016-7-16 上午09:49:25
     */
    public static byte[] encodeBase64String(String base64String){
        logger.debug("【History.FTP.FtpUtil】base64String 转byte[]");
        return Base64.decodeBase64(base64String);
    }

    /**
     * byte[]转base64Byte[]
     * @param binaryData
     * @return
     * @author zhangqh
     * @date 2016-7-16 上午09:53:30
     */
    public static byte[] encodeBase64(byte[] binaryData){
        logger.debug("【History.FTP.FtpUtil】byte[]转base64Byte[]");
        return Base64.encodeBase64(binaryData);
    }


    /**
     * 建立ftp连接对象
     * @return 是否连接成功 true /false
     * @throws NumberFormatException
     * @throws SocketException
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:12:22
     */
    public static boolean connection() throws NumberFormatException, SocketException, IOException{
        logger.debug("【History.FTP.FtpUtil】-开始建立ftp连接对象");
        //建立ftp连接
        ftpClient.connect(hostname, Integer.valueOf(port));
        ftpClient.setAutodetectUTF8(true);
        ftpClient.enterLocalPassiveMode();
        //A constant used to indicate the file(s) being transfered should be treated as a binary image, i.e., no translations should be performed.
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//设置以二进制传送
        return ftpClient.login(username, password);
    }

    /**
     * ftp是否已连接
     * @return true/false
     * @author zhangqh
     * @date 2016-7-16 上午10:15:01
     */
    public static boolean isConnected(){
        logger.debug("【History.FTP.FtpUtil】-ftp连接是否已经打开"+ftpClient.isConnected());
        return ftpClient.isConnected();
    }


    /**
     * 检测ftp上是否有该文件
     * @param remote 文件绝对URL
     * @return true/false
     * @author zhangqh
     * @date 2016-7-16 上午10:15:01
     */
    public static boolean isExisted (String remote) throws NumberFormatException, SocketException, IOException{
        logger.debug("【History.FTP.FtpUtil】-检测ftp上是否有该文件");
        InputStream inputStream = ftpClient.retrieveFileStream(remote);
        if(inputStream == null || ftpClient.getReplyCode() == FTPReply.FILE_UNAVAILABLE){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * 根据文件URL获取OutputStream
     * @param remote 文件绝对URL
     * @return OutputStream
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:19:14
     */
    public static OutputStream getFtpFileOutputStream(String remote) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-根据文件URL获取OutputStream");
        return ftpClient.storeFileStream(remote);
    }
    /**
     * 根据文件URL获取InputStream
     * @param remote 文件绝对URL
     * @return InputStream
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:19:14
     */
    public static InputStream getFtpFileInputStream(String remote) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-根据文件URL获取InputStream");
        return ftpClient.retrieveFileStream(remote);
    }

    /**
     * 根据文件URL获下载文件
     * @param remote 文件绝对URL
     * @return
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:19:14
     */
    public static void getFtpFile(String remote,String filePath) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-根据文件URL获取InputStream");

        File localFile = new File(filePath);
        OutputStream os = new FileOutputStream(localFile);
        ftpClient.retrieveFile(remote, os);
        os.close();
    }

    /**
     * InputStream 转byte[]
     * @param inputStream 输入流
     * @return byte[]
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:22:50
     */
    public static byte[] getInputStreamToByte(InputStream inputStream) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-InputStream 转byte[]");
        return IOUtils.toByteArray(inputStream);
    }


    /**
     * OutputStream 转byte[]
     * @param outputStream 输出流
     * @return byte[]
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:22:50
     */
    public static byte[] getOutputStreamToByte(OutputStream outputStream) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-OutputStream 转byte[]");
        ByteArrayOutputStream byteArrayOutputStream=(ByteArrayOutputStream)outputStream;
        return byteArrayOutputStream.toByteArray();
    }
    /**
     * ftp文件的上传
     * @param remote 上传路径
     * @param local 文件流
     * @return boolean
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-17 下午01:18:51
     */
    public static boolean storeFile(String remote, InputStream local) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-文件上传");
        return ftpClient.storeFile(remote, local);
    }
    /**
     * ftp文件的上传
     * @param remote 上传路径
     * @param local 文件流
     * @return boolean
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-17 下午01:18:51
     */
    public static boolean storeUniqueFile(String remote, InputStream local) throws IOException{
        logger.debug("【History.FTP.FtpUtil】-文件唯一性上传");
        return ftpClient.storeUniqueFile(remote, local);
    }
    /**
     * 关闭ftp
     * @return boolean
     * @throws IOException
     * @author zhangqh
     * @date 2016-7-16 上午10:30:50
     */
    public static boolean logout() throws IOException{
        logger.debug("【History.FTP.FtpUtil】-关闭ftp");
        return ftpClient.logout();
    }

}