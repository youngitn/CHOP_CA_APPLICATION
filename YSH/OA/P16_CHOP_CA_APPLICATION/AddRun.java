package YSH.OA.P16_CHOP_CA_APPLICATION;

//YSH/OA/P16_CHOP_CA_APPLICATION/AddRun
import hr.common;

import java.io.File;
import java.sql.SQLException;
import java.util.Vector;

import jcx.db.talk;
import jcx.jform.hproc;
import jcx.util.convert;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ysp.service.BaseService;
import com.ysp.service.MailService;

public class AddRun extends hproc {

	public String action(String value) throws Throwable {

		synchronized (this) {
			doAdd();
		}
		return value;
	}

	/**
	 * 新增申請表單
	 * 
	 * @throws Throwable
	 */
	private void doAdd() throws Throwable {
			
		
		
		if (!getValue("PNO").trim().isEmpty()) {
			message("該表單已存在,請重起新單！");
		}else if (getValue("CHOP_COMPANY").trim().isEmpty()) {
			message("印章/電子憑證公司別！");
		}else {
			
			String pno = getPNO(getToday("YYYYmmdd"), "CHOP_CA_APPLICATION");
			String uploadString = "";
			setValue("PNO", pno);
			if (getValue("CHOP_FORM").trim().length() != 0){
				File F1 = getUploadFile("CHOP_FORM");
				if (F1 != null)
					uploadString = " " + F1 + " ";
			}
			System.out.println("------>"+getValue("CHOP_FORM"));
			
			talk t = getTalk();
			String sql = "Insert into CHOP_CA_APPLICATION (PNO, DATE, CPNYID, EMPID, APP_TYPE, CHOP_COMPANY, ORIG_KEEPER, CHOP_NO, MATERIAL, CHOP_TYPE, CHANGE_REASON, NEW_KEEPER, CHOP_TODO, CHOP_ITEM, ACT_DESTROY_DATE, DESTROY_TYPE, TO_DESTROY, TO_DESTROY_WATCH, NOTE, CHOP_FORM"
					+ ") VALUES ('"
					+ getValue("PNO")
					+ "','"
					+ getValue("DATE")
					+ "','"
					+ getValue("CPNYID")
					+ "','"
					+ getValue("EMPID")
					+ "','"
					+ getValue("APP_TYPE")
					+ "','"
					+ getValue("CHOP_COMPANY")
					+ "','"
					+ getValue("ORIG_KEEPER")
					+ "','"
					+ getValue("CHOP_NO") 
					+ "','"
					+ getValue("MATERIAL")
					+ "','"
					+ getValue("CHOP_TYPE")
					+ "','"
					+ getValue("CHANGE_REASON")
					+ "','"
					+ getValue("NEW_KEEPER")
					+ "','"
					+ getValue("CHOP_TODO")
					+ "','"
					+ getValue("CHOP_ITEM")
					+ "','"
					+ getValue("ACT_DESTROY_DATE")
					+ "','"
					+ getValue("DESTROY_TYPE")
					+ "','"
					+ getValue("TO_DESTROY") 
					+ "','"			
					+ getValue("TO_DESTROY_WATCH")
					+ "','"
					+ getValue("NOTE")
					+ "','"
					+ uploadString				
					+ "')";
			String now = getNow();
			String MUSER = getUser();
			//PNO,CPNYID,DATE,EMPID,CHOP_COMPANY,CHOP_USER,NOTE
			// String boss_lv1[][] =
			// t.queryFromPool("SELECT DEP_CHIEF FROM USER_INOFFICE_INFO_VIEW WHERE EMPID = '"+MUSER+"'");
			// String GeneralManager[][] =
			// t.queryFromPool("SELECT DEP_CHIEF FROM HRUSER_DEPT_BAS WHERE DEP_NO = '423'");
			String SIGN_LV = "直屬主管";
			// if (boss_lv1[0][0].equals(GeneralManager[0][0])){
			// SIGN_LV = "總經理";
			// }

			String sc1 = "insert into CHOP_CA_APPLICATION_FLOWC (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno
					+ "','"
					+ SIGN_LV
					+ "','"
					+ MUSER
					+ "','"
					+ now
					+ "','" + SIGN_LV + "')";
			String sc2 = "insert into CHOP_CA_APPLICATION_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno + "','待處理','" + MUSER + "','" + now + "','待處理')";
			String sc3 = "insert into CHOP_CA_APPLICATION_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno + "','直屬主管','" + MUSER + "','" + now + "','')";
			// String
			// sc3="insert into E_SALARY_SIGN_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"+pno+"','單位主管','"+MUSER+"','"+now+"','')";

			t.execFromPool(sql);
			t.execFromPool(sc1);
			t.execFromPool(sc2);
			t.execFromPool(sc3);
			sendMail(t, getValue("EMPID"));
			t.close();
			setEditable("SEND", false);

			// message("資料庫異動完成!");
		}
	}

	private String getPNO(String inToday, String inTableName)
			throws SQLException, Exception {
		int idx = 1;
		talk t = getTalk();
		String pno = inToday;
		String sql = "select max(pno) from " + inTableName
				+ " where pno like '" + inToday + "%'";

		String theMaxPNO[][];
		theMaxPNO = t.queryFromPool(sql);
		if (theMaxPNO[0][0] != null && !theMaxPNO[0][0].trim().equals("")) {
			pno = inToday
					+ StringUtils.leftPad(
							NumberUtils.toInt(theMaxPNO[0][0].substring(inToday
									.length())) + idx + "", 3, "0");
		} else {
			pno = inToday + StringUtils.leftPad(idx + "", 3, "0");
		}
		t.close();
		System.gc();
		return pno;
	}

	public void sendMail(talk t, String empid) throws Throwable {

		String sqlc = "SELECT HRADDR FROM HRSYS";
		String[][] HRADDR = t.queryFromPool(sqlc);

		String reEmpId = getBOS(t, empid);
		String[] usr = { getEmail(reEmpId) };
		String title1 = "";
		String name = getName(empid);
		String title = "主旨：(" + empid + ")" + name + "印章及電子憑證申請單，請進入系統簽核"
				+ title1.trim();
		String content = "請進入 eHR 系統簽核( <a href=\"" + HRADDR[0][0].trim()
				+ "\">按此連結</a>)<br>";
		content += "=========內容摘要=========<br>";
		content += "單號:" + getValue("PNO") + "<br>";
		content += "申請日期:" + getValue("DATE") + "<br>";
		BaseService service = new BaseService();
		MailService mail = new MailService(service);
		String sendRS = mail.sendMailbccUTF8(usr, title, content, null, "",
				"text/html");
		message("已通知簽核者!");

	}

	public String getBOS(talk t, String EMPID) throws Throwable {
		int level = 3;
		String MASTER[][] = t.queryFromPool(
				"select MASTERID from HRUSER where EMPID='"
						+ convert.ToSql(EMPID.trim()) + "'", 30);
		if (MASTER.length != 0) {
			if (!MASTER[0][0].trim().equals("")) {
				return MASTER[0][0].trim();
			}
		}
		Vector v = null;
		v = common.getBosses(t, EMPID.trim(), new Vector(), level);
		for (int i = 0; i < v.size(); i++) {
			String id1 = v.elementAt(i).toString().trim();
			if (id1.trim().equals(""))
				continue;
			if (id1.trim().equals(EMPID.trim()))
				continue;
			return id1.trim();
		}
		/*
		 * String BOSS=common.getBoss(EMPID.trim(),1);
		 * if(BOSS.trim().length()!=0) return BOSS.trim();
		 */
		return "admin";
	}

}
