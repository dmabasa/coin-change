package za.co.fnb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import za.co.fnb.common.Denomination;
import za.co.fnb.model.Change;
import za.co.fnb.model.Coin;
import za.co.fnb.model.Note;
import za.co.fnb.service.IDispenserService;

@Service
public class DispenserServiceImpl implements IDispenserService {

	public Map<String, Integer> getOptimalChange(BigDecimal amt) {
			
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		List<Change> changeList = new ArrayList<Change>();
		while(amt.compareTo(new BigDecimal(Denomination.HUNDRED_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.HUNDRED_RANDS.getValue()));
			
			Note note = new Note();
			note.setNoteCount(1);
			note.setDenomination(Denomination.HUNDRED_RANDS.getDenominator());
			changeList.add(note);
			
		}
		
		
		while(amt.compareTo(new BigDecimal(Denomination.FIFTY_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.FIFTY_RANDS.getValue()));
			Note note = new Note();
			note.setNoteCount(1);
			note.setDenomination(Denomination.FIFTY_RANDS.getDenominator());
			changeList.add(note);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.TWENTY_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.TWENTY_RANDS.getValue()));
			Note note = new Note();
			note.setNoteCount(1);
			note.setDenomination(Denomination.TWENTY_RANDS.getDenominator());
			changeList.add(note);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.TEN_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.TEN_RANDS.getValue()));
			Note note = new Note();
			note.setNoteCount(1);
			note.setDenomination(Denomination.TEN_RANDS.getDenominator());
			changeList.add(note);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.FIFTY_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.FIFTY_RANDS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.FIFTY_RANDS.getDenominator());
			changeList.add(coin);
			
		}
		
		
		while(amt.compareTo(new BigDecimal(Denomination.TWO_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.TWO_RANDS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.TWO_RANDS.getDenominator());
			changeList.add(coin);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.ONE_RANDS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.ONE_RANDS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.ONE_RANDS.getDenominator());
			changeList.add(coin);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.FIFTY_CENTS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.FIFTY_CENTS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.FIFTY_CENTS.getDenominator());
			changeList.add(coin);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.TWENTY_CENTS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.TWENTY_CENTS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.TWENTY_CENTS.getDenominator());
			changeList.add(coin);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.TEN_CENTS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.TEN_CENTS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.TEN_CENTS.getDenominator());
			changeList.add(coin);
			
		}
		
		while(amt.compareTo(new BigDecimal(Denomination.FIFTY_CENTS.getValue())) >= 0)
		{
			amt = amt.subtract(new BigDecimal(Denomination.FIFTY_CENTS.getValue()));
			Coin coin = new Coin();
			coin.setCoinCount(1);
			coin.setDenomination(Denomination.FIFTY_CENTS.getDenominator());
			changeList.add(coin);
			
		}
		
		
		
		for(Change change : changeList)
		{
			
			if(change instanceof Coin)
			{
				Integer value = map.get(change.getDenomination());
				if(value == null)
				{
					map.put(change.getDenomination(), ((Coin)change).getCoinCount());
				}else
				{
					value = value + ((Coin)change).getCoinCount();
					map.put(change.getDenomination(), value);
				}
			}
			
			if(change instanceof Note)
			{
				Integer value = map.get(change.getDenomination());
				if(value == null)
				{
					map.put(change.getDenomination(), ((Note)change).getNoteCount());
				}else
				{
					value = value + ((Note)change).getNoteCount();
					map.put(change.getDenomination(), value);
				}
			}	
			
		}
		return map;
	}

}
