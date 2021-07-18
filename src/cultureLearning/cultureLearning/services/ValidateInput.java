package cultureLearning.cultureLearning.services;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ValidateInput extends AbstractValidator {

	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		String userInput = (String) ctx.getProperty().getValue();
		String name=(String) ctx.getProperty().getValue();
	
		
		
		if(userInput.isEmpty()) {
			addInvalidMessage(ctx, "Uzuza hano");
		}
		
		
		if(name != null && name.trim().length()< 3) {
			addInvalidMessage(ctx, "Andika izina rifite byibura inyuguti 4");	
			
		}

	}

	}


