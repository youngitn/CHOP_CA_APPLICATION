package YSH.OA.P16_CHOP_CA_APPLICATION;
import java.util.Vector;
//YSH\OA\P16_CHOP_CA_APPLICATION\ORIG_KEEPER
import jcx.jform.bRule;
/**
 * 
 * @author YangTing
 *	�y�{�W�h�� - �L������_��O�ޤH
 *	���o��u�����O�JEMAKER�N�n�~��
 *
 */
public class ORIG_KEEPER extends bRule{
	public Vector<String> getIDs(String value)throws Throwable{
		// �^�ǭȬ� Vector contails �ŦX�o���W�檺�b��
		// value �� "�ΦL���ҭ�O�ޤH"
		Vector<String> id=new Vector<String>();
		
		id.addElement(getData("ORIG_KEEPER"));
		
		id.addElement("admin");
		return id;
	}
	public String getInformation(){
		return "---------------\u7528\u5370\u6191\u8b49\u539f\u4fdd\u7ba1\u4eba.Rule()----------------";
	}
}