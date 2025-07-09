package oi;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;
import javax.activation.*;
import Utilities.constants;

 
class mailer extends NewTest{
	String filename;
	 public String filePath=(System.getProperty("user.dir") + "\\ExcelData\\");
	
	public void sendmail(String file) {
		
		
		
		String cluster=file.replace(filePath, "");
		System.out.println("1");
		String clusters=cluster.replace(".xls", "");
		// TODO Auto-generated method stub
		  String to="sibmanic@cisco.com";//change accordingly  
		  String to1="aaphilis@cisco.com";
		  String to2="vigbalar@cisco.com";
		  String toalias="bci-sso-dev.cisco.com";
		  final String user="sibmanic@cisco.com";//change accordingly  
		  final String password=constants.pwd2;//change accordingly   
		  //1) get the session object     
		  Properties properties = System.getProperties();  
		  properties.setProperty("mail.smtp.host", "mail.cisco.com");  
		  properties.put("mail.smtp.auth", "true");  
		  
		  Session session = Session.getDefaultInstance(properties,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,password);
		   
		   }  
		  });  
		     
		  //2) compose message     
		  try{ 
			  
			
			
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(user));  
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1)); 
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to2)); 
		    //message.addRecipient(Message.RecipientType.TO,new InternetAddress(toalias)); 
		    message.setSubject("Automation Test Results of "+clusters);  
		      
		    //3) create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
		    messageBodyPart1.setText("Hi,"+ "\n"
		    		+ "PFA Automation Test Report"); 

		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 
		    MimeBodyPart messageBodyPart3 = new MimeBodyPart();
		    MimeBodyPart messageBodyPart4 = new MimeBodyPart();
		   
		    filename=file;
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(filename);  
		    
		    String emlrpt="C:\\Users\\sibmanic\\OneDrive - Cisco\\Documents\\bci\\BCI\\target\\classes\\META-INF\\maven\\BCI\\BCI\\test-output\\emailable-report.html";
		    DataSource source1 = new FileDataSource(emlrpt);  
		    messageBodyPart3.setDataHandler(new DataHandler(source1));  
		    messageBodyPart3.setFileName(emlrpt);
		    
		    
		    messageBodyPart4.setText("Hi,"+ "\n"
		    		+ "PFA Automation Test Report"); 
		    
		   
		  
		    System.out.println("5");
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		    multipart.addBodyPart(messageBodyPart3);
		    multipart.addBodyPart(messageBodyPart4);
	
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("message sent....");  
		   }catch (MessagingException ex) {ex.printStackTrace();}  	
	}

	
}  