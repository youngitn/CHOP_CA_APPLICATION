package YSH.OA.P16_CHOP_CA_APPLICATION;

//YSH/OA/P16_CHOP_CA_APPLICATION/Notify;
import jcx.jform.bNotify;

import java.util.*;

import jcx.util.*;
import jcx.db.*;

import com.ysp.service.BaseService;
import com.ysp.service.MailService;

public class Notify extends bNotify {
	BaseService service;

	public void actionPerformed(String value) throws Throwable {

		service = new BaseService();
		// get sign people
		Vector<?> vid = getEngagedPeople();
		if (vid.size() == 0)
			return;
		String PNO = getValue("PNO").trim();
		String EMPID = getValue("EMPID").trim();

		String name = getName(EMPID);
		talk t = getTalk();

		// service.getUserInfoBean(EMPID);
		MailService mail = new MailService(service);

		Vector<String> V2 = new Vector<String>();
		for (int i = 0; i < vid.size(); i++) {
			String sql = "select EMAIL from HRUSER where EMPID = '"
					+ convert.ToSql(vid.elementAt(i).toString()) + "' ";
			String r1[][] = t.queryFromPool(sql);
			if (r1.length == 0)
				continue;
			V2.addElement(r1[0][0].trim());
			vid.elementAt(i).toString();
		}
		if (V2.size() == 0)
			return;
		// String smtp = "10.1.1.60,25,ehr,ehr123";
		// String sender = "ehr@ysp.local";

		String smtp = (String) get("SYSTEM.POP3");
		if (smtp == null)
			smtp = "www.interinfo.com.tw";
		String sender = (String) get("SYSTEM.SEMAIL");
		if (sender == null)
			sender = "admin@interinfo.com.tw";
		String sqlc;
		// get sign-page link url.
		sqlc = "SELECT HRADDR FROM HRSYS";
		String[][] HRADDR = t.queryFromPool(sqlc);

		String sqlcommString = "SELECT F_INP_INFO FROM CHOP_CA_APPLICATION_FLOWC where PNO =  '"
				+ convert.ToSql(PNO) + "' ";
		String backString[][] = t.queryFromPool(sqlcommString);
		String backTitleString = "";
		if (backString[0][0].contains("退簽")) {
			backTitleString = "已退簽";
		} else {
			backTitleString = "並請進入eHR系統簽核";
		}

		String title = "(" + EMPID + ")" + name + "之印章及電子憑證申請單( " + PNO
				+ " ). " + backTitleString;
		String content = "";
		content += "主旨:" + title + "<br>";

		// 在收件人文字中 排除admin
		if (vid.indexOf("admin") >= 0) {
			vid.remove("admin");
		}
		content = "請進入 eHR 系統簽核( <a href=\"" + HRADDR[0][0].trim()
				+ "\">按此連結</a>)<br>";
		content += "=========內容摘要=========<br>";
		content += "單號:" + getValue("PNO") + "<br>";
		content += "申請日期:" + getValue("DATE") + "<br>";

		String usr[] = ((String[]) V2.toArray(new String[0]));

		String sendRS = mail.sendMailbccUTF8(usr, title, content, null, "",
				"text/html");
		t.close();
		if (sendRS.trim().equals("")) {
			message("EMAIL已寄出通知");
		} else {
			message("EMAIL寄出失敗");
		}
		return;
	}

	public String getInformation() {
		return "---------------\u8655\u4e3b\u7ba1.Notify()----------------";
	}
}
