package cultureLearning.cultureLearning.services.impl;




import java.util.Map;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;


public class AuthenticationService  implements Initiator {
	

	public void doInit(Page page, Map<String, Object> args) throws Exception {
	// TODO Auto-generated method stub
	if(Sessions.getCurrent().getAttribute("s_user")==null) {
		System.out.println("s_user");
		Executions.sendRedirect("/Login.zul");
		return;
	}}
}
