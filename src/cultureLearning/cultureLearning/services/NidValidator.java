package cultureLearning.cultureLearning.services;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class NidValidator extends AbstractValidator {

	@Override
	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		String nationalId = (String) ctx.getProperty().getValue();
		System.out.println("National Identification: "+nationalId);
		if (nationalId.isEmpty()) {
			addInvalidMessage(ctx, "Andika no y'irangamuntu!!!");
		}
		if(nationalId.length()!=16) {
			addInvalidMessage(ctx, "irangamuntu igomba kugira imibare 16!");
		}
		
	}

}
