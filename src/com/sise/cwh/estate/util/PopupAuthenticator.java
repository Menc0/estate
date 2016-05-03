package com.sise.cwh.estate.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PopupAuthenticator extends Authenticator{
private String username;
private String password;
public PopupAuthenticator(String username,String pwd){
this.username=username;
this.password=pwd;
}
public PasswordAuthentication getPasswordAuthentication(){
return new PasswordAuthentication(this.username,this.password);
}
} 
