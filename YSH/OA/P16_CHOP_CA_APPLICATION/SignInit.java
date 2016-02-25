package YSH.OA.P16_CHOP_CA_APPLICATION;
//YSH/OA/P16_CHOP_CA_APPLICATION/SignInit
import jcx.db.talk;
import jcx.jform.hproc;

public class SignInit extends hproc{

	@Override
	public String action(String arg0) throws Throwable {
		// TODO Auto-generated method stub
		String sql,HECNAME,DEP_NAME;
		talk t = getTalk();
		if (getValue("NEW_KEEPER").trim().length() != 0){
			sql = "select HECNAME,DEP_NAME from USER_INFO_VIEW where EMPID = '"+getValue("NEW_KEEPER")+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			DEP_NAME = ret[0][1];
			
			setValue("NEW_KEEPER_NAME",HECNAME);
			setValue("NEW_KEEPER_DEPT_NAME",DEP_NAME);
		}
		if (getValue("EMPID").trim().length() != 0){
			sql = "select HECNAME,DEP_NAME from USER_INFO_VIEW where EMPID = '"+getValue("EMPID")+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			DEP_NAME = ret[0][1];
			
			setValue("EMPID_NAME",HECNAME);
			setValue("DEPT_NO_NAME",DEP_NAME);
		}
		if (getValue("TO_DESTROY").trim().length() != 0){
			sql = "select HECNAME from USER_INFO_VIEW where EMPID = '"+getValue("TO_DESTROY")+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			
			
			setValue("TO_DESTROY_NAME",HECNAME);
			
		}
		if (getValue("TO_DESTROY_WATCH").trim().length() != 0){
			sql = "select HECNAME from USER_INFO_VIEW where EMPID = '"+getValue("TO_DESTROY_WATCH")+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			
			setValue("TO_DESTROY_WATCH_NAME",HECNAME);			
		}
		return arg0;
	}

}
