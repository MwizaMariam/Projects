package cultureLearning.cultureLearning.services;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class PasswordValidation  extends AbstractValidator{

	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
String email=(String) ctx.getProperty().getValue();
		
		if (email != null && email.trim().length() != 0) {
			
					if(!email.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.{6,}).*$")){
						
						
					
				addInvalidMessage(ctx, "Urugero:abc123!@");	
			}
		} else {
			
			addInvalidMessage(ctx, "Andika ijambobanga ryawe");	
		}		
		
	}

}
