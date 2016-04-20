package YSH.OA.P16_CHOP_CA_APPLICATION;

//YSH/OA/P16_CHOP_CA_APPLICATION/GoToDetail
import jcx.db.talk;
import jcx.jform.hproc;

public class GoToDetail extends hproc{

	@Override
	public String action(String arg0) throws Throwable {
		// TODO Auto-generated method stub
		
		talk t = getTalk();
		String sql;
		
		String HECNAME;
		String DEP_NAME;
				
		CHOP_CA_APPLICATION_DAO dao = new CHOP_CA_APPLICATION_DAO();
		CHOP_CA_APPLICATION_BEAN bean = dao.getBean(getValue("table1.PNO"),t );
		
		setValue("PNO",bean.getPNO());
		setValue("DATE",bean.getDATE());

		setValue("CPNYID",bean.getCPNYID());
		setValue("EMPID",bean.getEMPID());
		setValue("APP_TYPE",bean.getAPP_TYPE());
		setValue("CHOP_COMPANY",bean.getCHOP_COMPANY());
		setValue("ORIG_KEEPER",bean.getORIG_KEEPER());
		setValue("CHOP_NO",bean.getCHOP_NO());
		setValue("MATERIAL",bean.getMATERIAL());
		setValue("CHOP_TYPE",bean.getCHOP_TYPE());
		if (bean.getCHOP_TYPE().equals("1")|| bean.getCHOP_TYPE().equals("7")){
			setVisible("FINANCIAL_DIV", true);
			setValue("FINANCIAL_DIV",bean.getFINANCIAL_DIV());
			setValue("F_NAME", getName(bean.getFINANCIAL_DIV()));
		}
		setValue("CHANGE_REASON",bean.getCHANGE_REASON());
		setValue("NEW_KEEPER",bean.getNEW_KEEPER());
		setValue("CHOP_TODO",bean.getCHOP_TODO());
		setValue("CHOP_ITEM",bean.getCHOP_ITEM());
		setValue("ACT_DESTROY_DATE",bean.getACT_DESTROY_DATE());
		setValue("DESTROY_TYPE",bean.getDESTROY_TYPE());
		setValue("TO_DESTROY",bean.getTO_DESTROY());
		setValue("TO_DESTROY_WATCH",bean.getTO_DESTROY_WATCH());
		setValue("NOTE",bean.getNOTE());
		setValue("CHOP_FORM",bean.getCHOP_FORM());
		String FF = getValue("CHOP_FORM");
		if (FF.trim().length() != 0) {
			setVisible("CHOP_FORM", false);
			setValue("CHOP_FORM_DOWNLOAD", "<a style=\"font-size:150%\" href=\"" + getDownloadURL(FF.trim())
					+ "\">憑證樣式附件下載</a><br>");
		}
		setVisible("SEND", false);
		setVisible("QUERYPAGE", false);
		if (bean.getNEW_KEEPER().trim().length() != 0){
			sql = "select HECNAME,DEP_NAME from USER_INFO_VIEW where EMPID = '"+bean.getNEW_KEEPER()+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			DEP_NAME = ret[0][1];
			
			setValue("NEW_KEEPER_NAME",HECNAME);
			setValue("NEW_KEEPER_DEPT_NAME",DEP_NAME);
		}
		if (bean.getEMPID().trim().length() != 0){
			sql = "select HECNAME,DEP_NAME from USER_INFO_VIEW where EMPID = '"+bean.getEMPID()+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			DEP_NAME = ret[0][1];
			
			setValue("EMPID_NAME",HECNAME);
			setValue("DEPT_NO_NAME",DEP_NAME);
		}
		if (bean.getTO_DESTROY().trim().length() != 0){
			sql = "select HECNAME from USER_INFO_VIEW where EMPID = '"+bean.getTO_DESTROY()+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			
			
			setValue("TO_DESTROY_NAME",HECNAME);
			
		}
		if (bean.getTO_DESTROY_WATCH().trim().length() != 0){
			sql = "select HECNAME from USER_INFO_VIEW where EMPID = '"+bean.getTO_DESTROY_WATCH()+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			
			setValue("TO_DESTROY_WATCH_NAME",HECNAME);			
		}
		if (bean.getORIG_KEEPER().trim().length() != 0){
			sql = "select HECNAME from USER_INFO_VIEW where EMPID = '"+bean.getORIG_KEEPER()+"'";
			String[][] ret = t.queryFromPool(sql);

			HECNAME = ret[0][0];
			
			setValue("ORIG_KEEPER_NAME",HECNAME);			
		}
		
		
		return arg0;
	}
}
