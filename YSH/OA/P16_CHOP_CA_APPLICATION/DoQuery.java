package YSH.OA.P16_CHOP_CA_APPLICATION;
//YSH/OA/P16_CHOP_CA_APPLICATION/DoQuery
import java.util.Vector;

import jcx.db.talk;
import jcx.jform.hproc;
import jcx.util.convert;

public class DoQuery extends hproc {
	// �i�۩wHTML�����U��쪺�w�]�ȻP���s���ʧ@
	// �ǤJ�� value
	public String action(String value) throws Throwable {
		talk t = getTalk();
		String EDATE = getValue("EDATE");
		String SDATE = getValue("SDATE");
		String PNO = getValue("PNO_Q");
		String EMPID = getValue("EMPID_Q");
		String CPNYID = getValue("CPNYID_Q");
		String sql = "select PNO,CPNYID,DATE,EMPID,(select COCNAME from COMPANY where CPNYID = a.CHOP_COMPANY),APP_TYPE,CHOP_TYPE,(select F_INP_STAT from CHOP_CA_APPLICATION_FLOWC where PNO=a.PNO),'ñ�֬���','�ԲӸ�T'"
				+ " from CHOP_CA_APPLICATION a ";
		sql += "where 1=1";

		if (PNO != null && PNO.length() != 0)
			sql += " and PNO='" + PNO + "'";

		if (EMPID != null && EMPID.length() != 0)
			sql += " and EMPID='" + EMPID + "'";
		if (CPNYID != null && CPNYID.length() != 0)
			sql += " and CPNYID='" + CPNYID + "'";
		if (SDATE.trim().length() != 0 && EDATE.trim().length() != 0) {
			sql += " and DATE between '" + convert.ToSql(SDATE.trim())
					+ "' and '" + convert.ToSql(EDATE.trim()) + "'";
		} else if (SDATE.trim().length() == 0 && EDATE.trim().length() != 0) {
			sql += " and DATE <= '" + convert.ToSql(EDATE.trim()) + "'";
		} else if (SDATE.trim().length() != 0 && EDATE.trim().length() == 0) {
			sql += " and DATE >= '" + convert.ToSql(SDATE.trim()) + "'";
		}
		System.out.println(sql);
		String data[][] = t.queryFromPool(sql);
		if (data.length < 1) {
			message("�d�L���!");
		} else {
			for (int i = 0; i < data.length; i++) {
				String sqlc = "select COCNAME from COMPANY where CPNYID = '"
						+ convert.ToSql(data[i][1].trim()) + "'";
				String[][] ret = t.queryFromPool(sqlc);
				data[i][1] = ret[0][0];
				data[i][3] = data[i][3] + "-" + getName(data[i][3]);
				if (data[i][7].trim().equals("�k��"))
					data[i][7] = data[i][7].trim()
							+ "<font color=blue>(�w����)</font>";
				else {
					Vector people = getApprovablePeople("P.16�L���ιq�l���ҥӽг�ñ��",
							"a.PNO='" + data[i][0] + "'"); // ���o�Y�i��ڥi�Hñ�֤H��ID
					System.out.println("--------->" + people.elementAt(0));
					StringBuffer sb = new StringBuffer();
					if (people != null) {
						if (people.size() != 0) {
							sb.append("(");
							for (int j = 0; j < people.size(); j++) {
								if (j != 0)
									sb.append(",");
								String id1 = (String) people.elementAt(j);
								String name1 = getName(id1);
								sb.append(name1 + ":" + id1);
							}
							sb.append(")");
						}
					}
					data[i][7] = data[i][7].trim() + "<font color=red>(������)"
							+ sb.toString() + "</font>";
				}
			}

			setTableData("table1", data);
		}

		return value;
	}
}
