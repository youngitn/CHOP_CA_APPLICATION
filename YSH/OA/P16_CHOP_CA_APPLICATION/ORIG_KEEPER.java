package YSH.OA.P16_CHOP_CA_APPLICATION;
import java.util.Vector;
//YSH\OA\P16_CHOP_CA_APPLICATION\ORIG_KEEPER
import jcx.jform.bRule;
/**
 * 
 * @author YangTing
 *	流程規則表 - 印章憑證_原保管人
 *	其實這麼短直接嵌入EMAKER就好才對
 *
 */
public class ORIG_KEEPER extends bRule{
	public Vector<String> getIDs(String value)throws Throwable{
		// 回傳值為 Vector contails 符合這條規格的帳號
		// value 為 "用印憑證原保管人"
		Vector<String> id=new Vector<String>();
		
		id.addElement(getData("ORIG_KEEPER"));
		
		id.addElement("admin");
		return id;
	}
	public String getInformation(){
		return "---------------\u7528\u5370\u6191\u8b49\u539f\u4fdd\u7ba1\u4eba.Rule()----------------";
	}
}