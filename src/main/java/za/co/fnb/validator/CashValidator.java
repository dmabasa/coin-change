package za.co.fnb.validator;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import za.co.fnb.model.NoteAmountPurchaseAmount;

public class CashValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return NoteAmountPurchaseAmount.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		NoteAmountPurchaseAmount noteAmountPurchaseAmount = (NoteAmountPurchaseAmount) target;
		if(!StringUtils.hasLength(noteAmountPurchaseAmount.getNoteAmount()))
		{
			errors.rejectValue("noteAmount","noteAmount.required");
		}else
		{
			
			BigDecimal noteAmount = new BigDecimal(noteAmountPurchaseAmount.getNoteAmount());
			BigDecimal purchaseAmount = new BigDecimal(noteAmountPurchaseAmount.getPurchaseAmount());
			
			
			if(purchaseAmount.compareTo(noteAmount) > 0)
			{
				errors.rejectValue("noteAmount","noteAmount.greater");
			}
		}
	}
}
