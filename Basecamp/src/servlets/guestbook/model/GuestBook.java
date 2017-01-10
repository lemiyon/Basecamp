package servlets.guestbook.model;

import java.util.Date;

/* GuestBook
 * VO = DTO = Domain Object
 * 방명록 게시글의 값 객체를 맡고 있다.
 * 
 * 차후, Rombok을 사용해서 간단하게 만들 생각이다.
 * */
public class GuestBook {
	
	protected int g_id;
	protected String g_mail;
	protected String g_pwd;
	protected String g_contents;
	protected Date cre_date;
	protected Date mod_date;
	
	public int getG_id() {
		return g_id;
	}
	public GuestBook setG_id(int g_id) {
		this.g_id = g_id;
		return this;
	}
	public String getG_mail() {
		return g_mail;
	}
	public GuestBook setG_mail(String g_mail) {
		this.g_mail = g_mail;
		return this;
	}
	public String getG_pwd() {
		return g_pwd;
	}
	public GuestBook setG_pwd(String g_pwd) {
		this.g_pwd = g_pwd;
		return this;
	}
	public String getG_contents() {
		return g_contents;
	}
	public GuestBook setG_contents(String g_contents) {
		this.g_contents = g_contents;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public GuestBook setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public GuestBook setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
}
