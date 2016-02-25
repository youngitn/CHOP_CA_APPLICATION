package YSH.OA.P16_CHOP_CA_APPLICATION;

public class CHOP_CA_APPLICATION_BEAN {
	private String PNO;
	private String DATE;
	private String CPNYID;
	private String EMPID;
	private String APP_TYPE;
	private String CHOP_COMPANY;
	private String ORIG_KEEPER;
	private String CHOP_NO;
	private String MATERIAL;
	private String CHOP_TYPE;
	private String CHANGE_REASON;
	private String NEW_KEEPER;
	private String CHOP_TODO;
	private String CHOP_ITEM;
	private String ACT_DESTROY_DATE;
	private String DESTROY_TYPE;
	private String TO_DESTROY;
	private String TO_DESTROY_WATCH;
	private String NOTE;
	private String CHOP_FORM;

	
	public CHOP_CA_APPLICATION_BEAN(String pNO, String dATE, String cPNYID,
			String eMPID, String aPP_TYPE, String cHOP_COMPANY,
			String oRIG_KEEPER, String cHOP_NO, String mATERIAL,
			String cHOP_TYPE, String cHANGE_REASON, String nEW_KEEPER,
			String cHOP_TODO, String cHOP_ITEM, String aCT_DESTROY_DATE,
			String dESTROY_TYPE, String tO_DESTROY, String tO_DESTROY_WATCH,
			String nOTE, String cHOP_FORM) {
		super();
		PNO = pNO;
		DATE = dATE;
		CPNYID = cPNYID;
		EMPID = eMPID;
		APP_TYPE = aPP_TYPE;
		CHOP_COMPANY = cHOP_COMPANY;
		ORIG_KEEPER = oRIG_KEEPER;
		CHOP_NO = cHOP_NO;
		MATERIAL = mATERIAL;
		CHOP_TYPE = cHOP_TYPE;
		CHANGE_REASON = cHANGE_REASON;
		NEW_KEEPER = nEW_KEEPER;
		CHOP_TODO = cHOP_TODO;
		CHOP_ITEM = cHOP_ITEM;
		ACT_DESTROY_DATE = aCT_DESTROY_DATE;
		DESTROY_TYPE = dESTROY_TYPE;
		TO_DESTROY = tO_DESTROY;
		TO_DESTROY_WATCH = tO_DESTROY_WATCH;
		NOTE = nOTE;
		CHOP_FORM = cHOP_FORM;
	}

	public String getACT_DESTROY_DATE() {
		return ACT_DESTROY_DATE;
	}

	public void setACT_DESTROY_DATE(String ACT_DESTROY_DATE) {
		this.ACT_DESTROY_DATE = ACT_DESTROY_DATE;
	}

	public String getORIG_KEEPER() {
		return ORIG_KEEPER;
	}

	public void setORIG_KEEPER(String ORIG_KEEPER) {
		this.ORIG_KEEPER = ORIG_KEEPER;
	}

	public String getCHOP_NO() {
		return CHOP_NO;
	}

	public void setCHOP_NO(String CHOP_NO) {
		this.CHOP_NO = CHOP_NO;
	}

	public String getNOTE() {
		return NOTE;
	}

	public void setNOTE(String NOTE) {
		this.NOTE = NOTE;
	}

	public String getCHANGE_REASON() {
		return CHANGE_REASON;
	}

	public void setCHANGE_REASON(String CHANGE_REASON) {
		this.CHANGE_REASON = CHANGE_REASON;
	}

	public String getTO_DESTROY() {
		return TO_DESTROY;
	}

	public void setTO_DESTROY(String TO_DESTROY) {
		this.TO_DESTROY = TO_DESTROY;
	}

	public String getPNO() {
		return PNO;
	}

	public void setPNO(String PNO) {
		this.PNO = PNO;
	}

	public String getCHOP_COMPANY() {
		return CHOP_COMPANY;
	}

	public void setCHOP_COMPANY(String CHOP_COMPANY) {
		this.CHOP_COMPANY = CHOP_COMPANY;
	}

	public String getCHOP_FORM() {
		return CHOP_FORM;
	}

	public void setCHOP_FORM(String CHOP_FORM) {
		this.CHOP_FORM = CHOP_FORM;
	}

	public String getDESTROY_TYPE() {
		return DESTROY_TYPE;
	}

	public void setDESTROY_TYPE(String DESTROY_TYPE) {
		this.DESTROY_TYPE = DESTROY_TYPE;
	}

	public String getEMPID() {
		return EMPID;
	}

	public void setEMPID(String EMPID) {
		this.EMPID = EMPID;
	}

	public String getMATERIAL() {
		return MATERIAL;
	}

	public void setMATERIAL(String MATERIAL) {
		this.MATERIAL = MATERIAL;
	}

	public String getCHOP_TODO() {
		return CHOP_TODO;
	}

	public void setCHOP_TODO(String CHOP_TODO) {
		this.CHOP_TODO = CHOP_TODO;
	}

	public String getTO_DESTROY_WATCH() {
		return TO_DESTROY_WATCH;
	}

	public void setTO_DESTROY_WATCH(String TO_DESTROY_WATCH) {
		this.TO_DESTROY_WATCH = TO_DESTROY_WATCH;
	}

	public String getAPP_TYPE() {
		return APP_TYPE;
	}

	public void setAPP_TYPE(String APP_TYPE) {
		this.APP_TYPE = APP_TYPE;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String DATE) {
		this.DATE = DATE;
	}

	public String getCHOP_ITEM() {
		return CHOP_ITEM;
	}

	public void setCHOP_ITEM(String CHOP_ITEM) {
		this.CHOP_ITEM = CHOP_ITEM;
	}

	public String getCPNYID() {
		return CPNYID;
	}

	public void setCPNYID(String CPNYID) {
		this.CPNYID = CPNYID;
	}

	public String getNEW_KEEPER() {
		return NEW_KEEPER;
	}

	public void setNEW_KEEPER(String NEW_KEEPER) {
		this.NEW_KEEPER = NEW_KEEPER;
	}

	public String getCHOP_TYPE() {
		return CHOP_TYPE;
	}

	public void setCHOP_TYPE(String CHOP_TYPE) {
		this.CHOP_TYPE = CHOP_TYPE;
	}

	@Override
	public String toString() {
		return "ClassPojo [ACT_DESTROY_DATE = " + ACT_DESTROY_DATE
				+ ", ORIG_KEEPER = " + ORIG_KEEPER + ", CHOP_NO = " + CHOP_NO
				+ ", NOTE = " + NOTE + ", CHANGE_REASON = " + CHANGE_REASON
				+ ", TO_DESTROY = " + TO_DESTROY + ", PNO = " + PNO
				+ ", CHOP_COMPANY = " + CHOP_COMPANY + ", CHOP_FORM = "
				+ CHOP_FORM + ", DESTROY_TYPE = " + DESTROY_TYPE + ", EMPID = "
				+ EMPID + ", MATERIAL = " + MATERIAL + ", CHOP_TODO = "
				+ CHOP_TODO + ", TO_DESTROY_WATCH = " + TO_DESTROY_WATCH
				+ ", APP_TYPE = " + APP_TYPE + ", DATE = " + DATE
				+ ", CHOP_ITEM = " + CHOP_ITEM + ", CPNYID = " + CPNYID
				+ ", NEW_KEEPER = " + NEW_KEEPER + ", CHOP_TYPE = " + CHOP_TYPE
				+ "]";
	}
}
