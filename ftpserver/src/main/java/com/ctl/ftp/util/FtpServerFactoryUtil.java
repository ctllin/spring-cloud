package com.ctl.ftp.util;

import java.util.ArrayList;
import java.util.List;

import com.ctl.ftp.util.ConfigUtils;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;


/**
 * 引用到这三个包 ftplet-api-1.1.1.jar ftpserver-core-1.1.1.jar mina-core-2.0.16.jar
 * http://mina.apache.org/ftpserver-project/embedding_ftpserver.html
 * 
 * @author ctl
 * 
 */
public class FtpServerFactoryUtil {
	public static FtpServer server;
	public static void main(String[] args) throws FtpException {
		startFtpServer();
		stopFtpServer();
	}
	public static void stopFtpServer() {
		if(server!=null&&!server.isStopped()){
			server.stop();
		}
	}
	public static void startFtpServer() throws FtpException {
		if(server==null||server.isStopped()){
			FtpServerFactory serverFactory = new FtpServerFactory();
			ListenerFactory factory = new ListenerFactory();
			// set the port of the listener
			factory.setPort(Integer.parseInt(ConfigUtils.getType("ftp.local.port")));
			// replace the default listener
			serverFactory.addListener("default", factory.createListener());
			//设置用户
			BaseUser user = new BaseUser();
			user.setName(ConfigUtils.getType("ftp.local.username"));
			user.setPassword(ConfigUtils.getType("ftp.local.password"));
			user.setHomeDirectory(ConfigUtils.getType("ftp.local.homeDirectory"));
			//设置权限
			List<Authority> authorities = new ArrayList<Authority>();  
		    authorities.add(new WritePermission()); 
		    user.setAuthorities(authorities);  
			serverFactory.getUserManager().save(user);
			server = serverFactory.createServer();
			server.start();
		}else{
			
		}
	}

}
