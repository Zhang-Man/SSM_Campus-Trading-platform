package com.substitute.util;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import java.util.Date;
import java.util.Properties;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
public class MailUtils {
	//修改发件人的邮箱地址和密码
    private static String myEmailAccount = "15547873739@163.com";
    private static String myEmailPassword = "project123456789";
    //设置SMTP地址
    private static String myEmailSMTPHost = "smtp.163.com";
    //设置链接属性
    private static Properties props;
    
    public static String getMyEmailAccount() {
		return myEmailAccount;
	}

	public static void setMyEmailAccount(String myEmailAccount) {
		MailUtils.myEmailAccount = myEmailAccount;
	}

	public static String getMyEmailPassword() {
		return myEmailPassword;
	}

	public static void setMyEmailPassword(String myEmailPassword) {
		MailUtils.myEmailPassword = myEmailPassword;
	}

	public static String getMyEmailSMTPHost() {
		return myEmailSMTPHost;
	}

	public static void setMyEmailSMTPHost(String myEmailSMTPHost) {
		MailUtils.myEmailSMTPHost = myEmailSMTPHost;
	}

	static {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
    	props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");    
    }
	/**
	 * @param SendUserName 发件人名字
	 * @param receiveMailAccount 收件人邮箱
	 * @param MessageString 发送内容
	 * @throws Exception
	 */
    public static void SendMail(String receiveMailAccount,String MessageString,String SendUserName){
        Session session = Session.getInstance(props);
        session.setDebug(false);
        MimeMessage message =null;
        Transport transport =null;
		try {
			message = createMimeMessage(session, myEmailAccount, receiveMailAccount,MessageString, SendUserName, "验证码");
			transport = session.getTransport();
	        transport.connect(myEmailAccount, myEmailPassword);
	        transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String MessageString,String SendUser,String Title) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, SendUser, "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "UTF-8"));
        message.setSubject(Title, "UTF-8");
        message.setContent(MessageString, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    @Test
    public void test() {
    	SendMail("lilei.kuge@qq.com","123098","corasun项目组");
	}
}
