package cultureLearning.cultureLearning.services;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class EmailValidation extends AbstractValidator {

	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		String email=(String) ctx.getProperty().getValue();
		
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
		
				addInvalidMessage(ctx, "Andika neza imeri yawe!");	
			}
		} else {
			
			addInvalidMessage(ctx, "andika emeri yawe");	
		}		
		
	}


}
