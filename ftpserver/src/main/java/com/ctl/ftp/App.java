package com.ctl.ftp;

import com.ctl.ftp.util.FtpServerFactoryUtil;
import org.apache.ftpserver.ftplet.FtpException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            FtpServerFactoryUtil.startFtpServer();
        } catch (FtpException e) {
            System.out.println("ftpserver start fail");
        }
    }
}
