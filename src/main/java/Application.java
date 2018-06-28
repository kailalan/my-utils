
import History.FTP.nonstatic_class.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
    public static void main(String[] args) throws NumberFormatException, SocketException, IOException {
        String localPath = "D:/";
        String fileName = "wu.shanghui.jpg";
        String filePath = localPath + File.separatorChar + fileName;

        File localFile = new File(filePath);

        FtpUtil ftp =  new FtpUtil(1000,1000,100);



        ftp.connect("10.1.128.213",21,"ftpuser","YXftP#2016",false);

        //ftp.list("userphoto");


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date filterDate = formatter.parse("2018-06-25 09:20:49", pos);
        ftp.listFileByTime("userphoto",filterDate);
        //FtpUtil.isExisted("userphoto/0.jpg");
        //FtpUtil.getFtpFile("userphoto/zhu.chunxiu.jpg",filePath);

    }



}
