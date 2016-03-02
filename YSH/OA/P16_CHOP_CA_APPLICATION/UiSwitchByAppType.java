package YSH.OA.P16_CHOP_CA_APPLICATION;
import jcx.db.talk;
//YSH/OA/P16_CHOP_CA_APPLICATION/UiSwitchByAppType
import jcx.jform.hproc;

public class UiSwitchByAppType extends hproc {

	@Override
	public String action(String arg0) throws Throwable {
		// TODO Auto-generated method stub
		talk t = getTalk();
		if (arg0.length() == 0)
			arg0 = "0";
		int flag = Integer.parseInt(arg0);
		
		//1.新申請2.損毀重新申請3.加刻4.報銷5.移轉
		switch (flag) {
		case 1:
			setEditable("ORIG_KEEPER", false);
			setEditable("CHOP_NO", false);
			setEditable("MATERIAL", true);
			setEditable("CHOP_TYPE", true);
			setEditable("CHANGE_REASON", false);
			setEditable("NEW_KEEPER", true);
			setEditable("CHOP_TODO_SELECT", true);
			setEditable("CHOP_TODO", true);
			setEditable("CHOP_ITEM", true);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", false);
			setEditable("TO_DESTROY_WATCH", false);
			setValue("TO_DESTROY","");
			setValue("TO_DESTROY_WATCH", "");
			setValue("TO_DESTROY_NAME","");
			setValue("TO_DESTROY_WATCH_NAME","");
			break;
		case 2:
			setEditable("ORIG_KEEPER", true);
			setEditable("CHOP_NO", true);
			setEditable("MATERIAL", true);
			setEditable("CHOP_TYPE", true);
			setEditable("CHANGE_REASON", false);
			setEditable("NEW_KEEPER", false);
			setEditable("CHOP_TODO_SELECT", true);
			setEditable("CHOP_TODO", true);
			setEditable("CHOP_ITEM", true);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", false);
			setEditable("TO_DESTROY_WATCH", false);
			setValue("TO_DESTROY","");
			setValue("TO_DESTROY_WATCH", "");
			setValue("TO_DESTROY_NAME","");
			setValue("TO_DESTROY_WATCH_NAME","");
			setValue("NEW_KEEPER","");
			setValue("NEW_KEEPER_NAME","");
			setValue("NEW_KEEPER_DEPT_NAME","");
			break;
		case 3:
			setEditable("ORIG_KEEPER", true);
			setEditable("CHOP_NO", true);
			setEditable("MATERIAL", true);
			setEditable("CHOP_TYPE", true);
			setEditable("CHANGE_REASON", true);
			setEditable("NEW_KEEPER", false);
			setEditable("CHOP_TODO_SELECT", true);
			setEditable("CHOP_TODO", true);
			setEditable("CHOP_ITEM", true);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", false);
			setEditable("TO_DESTROY_WATCH", false);
			setValue("TO_DESTROY","");
			setValue("TO_DESTROY_WATCH", "");
			setValue("TO_DESTROY_NAME","");
			setValue("TO_DESTROY_WATCH_NAME","");
			setValue("NEW_KEEPER","");
			setValue("NEW_KEEPER_NAME","");
			setValue("NEW_KEEPER_DEPT_NAME","");
			break;
		case 4:
			setEditable("ORIG_KEEPER", true);
			setEditable("CHOP_NO", true);
			setEditable("MATERIAL", true);
			setEditable("CHOP_TYPE", true);
			setEditable("CHANGE_REASON", true);
			setEditable("NEW_KEEPER", false);
			setEditable("CHOP_TODO_SELECT", true);
			setEditable("CHOP_TODO", true);
			setEditable("CHOP_ITEM", true);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", true);
			setEditable("TO_DESTROY_WATCH", true);
			//t.queryFromPool("select id from HRUSER_DEPT where DEP_NO = ''")[0][0];
			String dnameString = t.queryFromPool("select id from HRUSER_DEPT where DEP_NO = '100637'")[0][0];
			String dnamewString = t.queryFromPool("select id from HRUSER_DEPT where DEP_NO = '100638'")[0][0];
			setValue("TO_DESTROY",dnameString);
			setValue("TO_DESTROY_WATCH", dnamewString);
			setValue("TO_DESTROY_NAME",getName(dnameString));
			setValue("TO_DESTROY_WATCH_NAME",getName(dnamewString));
			setValue("NEW_KEEPER","");
			setValue("NEW_KEEPER_NAME","");
			setValue("NEW_KEEPER_DEPT_NAME","");
			break;
		case 5:
			setEditable("ORIG_KEEPER", true);
			setEditable("CHOP_NO", true);
			setEditable("MATERIAL", true);
			setEditable("CHOP_TYPE", true);
			setEditable("CHANGE_REASON", true);
			setEditable("NEW_KEEPER", true);
			setEditable("CHOP_TODO_SELECT", true);
			setEditable("CHOP_TODO", true);
			setEditable("CHOP_ITEM", true);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", false);
			setEditable("TO_DESTROY_WATCH", false);
			setValue("TO_DESTROY","");
			setValue("TO_DESTROY_WATCH", "");
			setValue("TO_DESTROY_NAME","");
			setValue("TO_DESTROY_WATCH_NAME","");
			
			break;
		case 0:
			setEditable("ORIG_KEEPER", false);
			setEditable("CHOP_NO", false);
			setEditable("MATERIAL", false);
			setEditable("CHOP_TYPE", false);
			setEditable("CHANGE_REASON", false);
			setEditable("NEW_KEEPER", false);
			setEditable("CHOP_TODO_SELECT", false);
			setEditable("CHOP_TODO", false);
			setEditable("CHOP_ITEM", false);
			setEditable("ACT_DESTROY_DATE", false);
			setEditable("DESTROY_TYPE", false);
			setEditable("TO_DESTROY", false);
			setEditable("TO_DESTROY_WATCH", false);
			setValue("TO_DESTROY","");
			setValue("TO_DESTROY_WATCH", "");
			setValue("TO_DESTROY_NAME","");
			setValue("TO_DESTROY_WATCH_NAME","");
			break;

		default:
			break;
		}

		String[][] rets = t.queryFromPool("select HECNAME,DEP_NAME from USER_INFO_VIEW where EMPID = '"+getValue("EMPID").trim()+"'");

		String HECNAME = rets[0][0];	
		String DEP_NAME = rets[0][1];


		setValue("EMPID_NAME",HECNAME);
		setValue("DEPT_NO_NAME",DEP_NAME);

		return arg0;
	}
}