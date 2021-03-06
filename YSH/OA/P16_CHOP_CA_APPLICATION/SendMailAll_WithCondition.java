package YSH.OA.P16_CHOP_CA_APPLICATION;

//YSH/OA/P16_CHOP_CA_APPLICATION/SendMailAll_WithCondition;
import java.util.Arrays;
import java.util.HashSet;

import jcx.db.talk;
import jcx.jform.bProcFlow;
import jcx.servlet.common.organizationServlet;
import jcx.util.convert;

import com.ysp.service.BaseService;
import com.ysp.service.MailService;

public class SendMailAll_WithCondition extends bProcFlow {

	public boolean action(String value) throws Throwable {
		// 回傳值為 true 表示執行接下來的流程處理
		// 回傳值為 false 表示接下來不執行任何流程處理
		// 傳入值 value 為 "核准"
			
		String checkString = getValue("APP_TYPE").trim();
		if (!checkString.equals("1") && !checkString.equals("5")
				&& !checkString.equals("4")) {
			talk t = getTalk();
			BaseService service = new BaseService();
			MailService mail = new MailService(service);

			String PNO = getValue("PNO").trim();
			String sql = "UPDATE CHOP_CA_APPLICATION set NEW_CHOP_NO = '"+getValue("SET_NEW_CHOP_NO").trim()+"' where PNO = '"+PNO+"';";
			System.out.println(sql);
			t.execFromPool(sql);
			String EMPID;
			String name;
			String title;
			String content = "";
			String smtp = (String) get("SYSTEM.POP3");
			if (smtp == null)
				smtp = "www.interinfo.com.tw";
			String sender = (String) get("SYSTEM.SEMAIL");
			if (sender == null)
				sender = "admin@interinfo.com.tw";
			String email = "";
			String sendRS = "";
			// getAllApprovePeople is a local method.
			String[] AllApprovePeople = getAllApprovePeople();
			int isEmailAllSend = 0;

			for (String peopleString : AllApprovePeople) {
				// System.out.println("value=" + it.next().toString());
				content = "";
				EMPID = getValue("EMPID").trim();
				name = getName(EMPID);
				title = "(" + EMPID + ")" + name + "之印章及電子憑證申請單( " + PNO
						+ " ) 已結案";

				content += "主旨:" + title + "<br>";

				email = getEmail(peopleString);
				// email = service.getUserInfoBean(peopleString).getEmail();
				String usr[] = { email };

				sendRS = mail.sendMailbccUTF8(usr, title, content, null, "",
						"text/html");
				// if send mail Sending Failed,isEmailAllSend will +1 for check.
				if (!sendRS.trim().equals("")) {
					isEmailAllSend++;
				}

			}
			t.close();
			if (isEmailAllSend != 0) {
				message("EMAIL寄出失敗");
				return false;

			}

			message("EMAIL已寄出通知");
		}

		return true;

	}

	public String getInformation() {
		return "---------------\u6838\u51c6.preProcess()----------------";
	}

	public String[] getAllApprovePeople() {
		String vid[][] = getFlowHistory();
		String ausr[] = new String[vid.length];
		for (int i = 0; i < vid.length; i++) {
			ausr[i] = vid[i][1].trim();
		}
		HashSet<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(ausr));
		String usr[] = (String[]) set.toArray(new String[0]);
		return usr;

	}
}
