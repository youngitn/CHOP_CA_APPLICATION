package YSH.OA.P16_CHOP_CA_APPLICATION;

//YSH/OA/P16_CHOP_CA_APPLICATION/AddRun
import hr.common;

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
	 * �s�W�ӽЪ��
	 * 
	 * @throws Throwable
	 */
	private void doAdd() throws Throwable {
		
		int chk = 0;
		for (int i = 1; i <= 3; i++) {
			System.out.println("CHOP"+Integer.toString(i) +"_NO");
			if(getValue("CHOP"+Integer.toString(i) +"_NO").trim().length() > 0){
				System.out.println("in");
				if (getValue("CHOP"+Integer.toString(i)+"_TODO").trim().isEmpty()||
					getValue("CHOP"+Integer.toString(i)+"_PROCESS_DEPT").trim().isEmpty()||
					getValue("CHOP"+Integer.toString(i)+"_SIGN_LV").trim().isEmpty()){
					System.out.println("in2");
					chk++;
				}
			}
		}
		
		
		
		if (!getValue("PNO").trim().isEmpty()) {
			message("�Ӫ��w�s�b,�Э��_�s��I");
		}else if (getValue("CHOP_COMPANY").trim().isEmpty()) {
			message("�п�ܦL�����q�O�I");
		}else if (getValue("CHOP1_NO").trim().isEmpty()) {
			message("�п�ܦL���s���I");
		}else if (getValue("CHOP1_SIGN_LV").trim().isEmpty()) {
			message("�п�ܮ֨M�v���I");
		}else if (getValue("CHOP_USER").trim().isEmpty()) {
			message("�п�ܥΦL�H�I");
		}else if (getValue("CHOP1_PROCESS_DEPT").trim().isEmpty()){
			message("�п�ܩӿ���I");
		}else if (getValue("CHOP1_TODO").trim().isEmpty()){
			message("�п�ܥγ~���O�I");
		}else if (chk > 0){
			message("���ˬd�w��ܦL���s�����������O�_��J����!");
			System.out.println("chk > 0");
		}else {
			
			String pno = getPNO(getToday("YYYYmmdd"), "CHOP_APPLICATION");

			setValue("PNO", pno);

			talk t = getTalk();
			String sql = "Insert into CHOP_APPLICATION (PNO,"
					+ "EMPID,"
					+ "CPNYID,"
					+ "APP_TYPE,"
					+ "CHOP_COMPANY,"
					+ "CHANGE_TYPE,"
					+ "CHOP1_NO,"
					+ "CHOP1_TODO,"
					+ "CHOP1_PROCESS_DEPT,"
					+ "CHOP1_SIGN_LV,"
					+ "CHOP1_NOTE,"
					+ "CHOP2_NO,"
					+ "CHOP2_TODO,"
					+ "CHOP2_PROCESS_DEPT,"
					+ "CHOP2_SIGN_LV,"
					+ "CHOP2_NOTE,"
					+ "CHOP3_NO,"
					+ "CHOP3_TODO,"
					+ "CHOP3_PROCESS_DEPT,"
					+ "CHOP3_SIGN_LV,"
					+ "CHOP3_NOTE,"
					+ "CHOP_USER,"
					+ "NOTE,"
					+ "DATE"
					+ ") VALUES ('"
					+ getValue("PNO")
					+ "','"
					+ getValue("EMPID")
					+ "','"
					+ getValue("CPNYID")
					+ "','"
					+ getValue("APP_TYPE")
					+ "','"
					+ getValue("CHOP_COMPANY")
					+ "','"
					+ getValue("CHANGE_TYPE")
					+ "','"
					+ getValue("CHOP1_NO") 
					+ "','"
					+ getValue("CHOP1_TODO")
					+ "','"
					+ getValue("CHOP1_PROCESS_DEPT")
					+ "','"
					+ getValue("CHOP1_SIGN_LV")
					+ "','"
					+ getValue("CHOP1_NOTE")
					+ "','"
					+ getValue("CHOP2_NO")
					+ "','"
					+ getValue("CHOP2_TODO")
					+ "','"
					+ getValue("CHOP2_PROCESS_DEPT")
					+ "','"
					+ getValue("CHOP2_SIGN_LV")
					+ "','"
					+ getValue("CHOP2_NOTE") 
					+ "','"			
					+ getValue("CHOP3_NO")
					+ "','"
					+ getValue("CHOP3_TODO")
					+ "','"
					+ getValue("CHOP3_PROCESS_DEPT")
					+ "','"
					+ getValue("CHOP3_SIGN_LV")
					+ "','"
					+ getValue("CHOP3_NOTE")
					+ "','"
					+ getValue("CHOP_USER")
					+ "','"
					+ getValue("NOTE")
					+ "','"
					+ getValue("DATE")					
					+ "')";
			String now = getNow();
			String MUSER = getUser();
			//PNO,CPNYID,DATE,EMPID,CHOP_COMPANY,CHOP_USER,NOTE
			// String boss_lv1[][] =
			// t.queryFromPool("SELECT DEP_CHIEF FROM USER_INOFFICE_INFO_VIEW WHERE EMPID = '"+MUSER+"'");
			// String GeneralManager[][] =
			// t.queryFromPool("SELECT DEP_CHIEF FROM HRUSER_DEPT_BAS WHERE DEP_NO = '423'");
			String SIGN_LV = "���ݥD��";
			// if (boss_lv1[0][0].equals(GeneralManager[0][0])){
			// SIGN_LV = "�`�g�z";
			// }

			String sc1 = "insert into CHOP_APPLICATION_FLOWC (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno
					+ "','"
					+ SIGN_LV
					+ "','"
					+ MUSER
					+ "','"
					+ now
					+ "','" + SIGN_LV + "')";
			String sc2 = "insert into CHOP_APPLICATION_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno + "','�ݳB�z','" + MUSER + "','" + now + "','�ݳB�z')";
			String sc3 = "insert into CHOP_APPLICATION_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"
					+ pno + "','���ݥD��','" + MUSER + "','" + now + "','')";
			// String
			// sc3="insert into E_SALARY_SIGN_FLOWC_HIS (PNO,F_INP_STAT,F_INP_ID,F_INP_TIME,F_INP_INFO) values ('"+pno+"','���D��','"+MUSER+"','"+now+"','')";

			t.execFromPool(sql);
			t.execFromPool(sc1);
			t.execFromPool(sc2);
			t.execFromPool(sc3);
			sendMail(t, getValue("EMPID"));
			t.close();
			setEditable("SEND", false);

			// message("��Ʈw���ʧ���!");
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
		String title = "�D���G(" + empid + ")" + name + "�ΦL���غ[�֨M�v���ӽЪ�A�жi�J�t��ñ��"
				+ title1.trim();
		String content = "�жi�J eHR �t��ñ��( <a href=\"" + HRADDR[0][0].trim()
				+ "\">�����s��</a>)<br>";
		content += "=========���e�K�n=========<br>";
		content += "�渹:" + getValue("PNO") + "<br>";
		content += "�ӽФ��:" + getValue("DATE") + "<br>";
		BaseService service = new BaseService();
		MailService mail = new MailService(service);
		String sendRS = mail.sendMailbccUTF8(usr, title, content, null, "",
				"text/html");
		message("�w�q��ñ�֪�!");

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
