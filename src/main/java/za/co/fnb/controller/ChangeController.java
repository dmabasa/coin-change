package za.co.fnb.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.fnb.model.NoteAmountPurchaseAmount;
import za.co.fnb.model.OptimalChange;
import za.co.fnb.service.IDispenserService;
import za.co.fnb.validator.CashValidator;


@PropertySource("classpath:application.properties")
@Controller
public class ChangeController {

	@Autowired
	IDispenserService dispenserService; // Service which will do all manipulation
										// work
	
	@Resource
	private Environment env;
	
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public String getOptimalChange(@ModelAttribute("noteAmountPurchaseAmount") @Valid NoteAmountPurchaseAmount noteAmountPurchaseAmount,
			ModelMap model, BindingResult result) {
		
		String purchaseAmount = noteAmountPurchaseAmount.getPurchaseAmount();
		
		noteAmountPurchaseAmount.setPurchaseAmount(purchaseAmount);
		
		new CashValidator().validate(noteAmountPurchaseAmount, result);
		
		if(result.hasErrors()) {
			
			return "dispenser";
	    }
		
		BigDecimal purchaseAmt = new BigDecimal(noteAmountPurchaseAmount.getPurchaseAmount());

		List<OptimalChange> changeList = new ArrayList<OptimalChange>();
		
		BigDecimal amt = new BigDecimal(noteAmountPurchaseAmount.getNoteAmount());
		
		amt = amt.subtract(purchaseAmt);
		Map<String, Integer> map = dispenserService.getOptimalChange(amt);
		
		for(Map.Entry<String, Integer> entry : map.entrySet())
		{
			OptimalChange optimalChange = new OptimalChange();
			optimalChange.setCount(entry.getValue());
			optimalChange.setDenominator(entry.getKey());
			changeList.add(optimalChange);
		}
		model.addAttribute("changeList", changeList);
		return "changebreakdown";
	}
	

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dispenser(ModelMap model) {
		
		String purchaseAmount = env.getRequiredProperty("purchase.amount");
		
		NoteAmountPurchaseAmount noteAmountPurchaseAmount = new NoteAmountPurchaseAmount();
		noteAmountPurchaseAmount.setPurchaseAmount(purchaseAmount);
		
		model.addAttribute("noteAmountPurchaseAmount", noteAmountPurchaseAmount);
		return "dispenser";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String reset(ModelMap model) {
		String purchaseAmount = env.getRequiredProperty("purchase.amount");
		
		NoteAmountPurchaseAmount noteAmountPurchaseAmount = new NoteAmountPurchaseAmount();
		noteAmountPurchaseAmount.setPurchaseAmount(purchaseAmount);
		
		model.addAttribute("noteAmountPurchaseAmount", noteAmountPurchaseAmount);
		return "dispenser";
	}
	
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
