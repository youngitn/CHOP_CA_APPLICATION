package YSH.OA.P16_CHOP_CA_APPLICATION;

import java.sql.SQLException;

import jcx.db.talk;

public class CHOP_CA_APPLICATION_DAO {

	public CHOP_CA_APPLICATION_BEAN getBean(String pno, talk t)
			throws SQLException, Exception {
		String ret[][] = t
				.queryFromPool("select * from CHOP_CA_APPLICATION where PNO = '"
						+ pno + "'");

		CHOP_CA_APPLICATION_BEAN b = new CHOP_CA_APPLICATION_BEAN(ret[0][0],
				ret[0][1], ret[0][2], ret[0][3], ret[0][4], ret[0][5],
				ret[0][6], ret[0][7], ret[0][8], ret[0][9], ret[0][10],
				ret[0][11], ret[0][12], ret[0][13], ret[0][14], ret[0][15],
				ret[0][16], ret[0][17], ret[0][18], ret[0][19], ret[0][20]);

		return b;

	}
}
