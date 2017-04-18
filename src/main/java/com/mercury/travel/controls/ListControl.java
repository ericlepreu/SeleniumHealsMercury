package com.mercury.travel.controls;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//this is for a standard list control, where DOM looks like:
// <select name="fromMonth"> == $0
//   <option value="1">January</option>
//	 <option value="2">February</option>
//   <option value="3">March</option>
//   ...
public class ListControl {	
	//Select LOV value based on displayed text (such as January in example above)
	//Use Example:   ListControl.selectByVisibleText(lstFromMonth, "March");
	static public void selectByVisibleText(WebElement list, String visibleValue) throws Exception {
		try {
			Select dropdown = new Select(list); 
			dropdown.selectByVisibleText(visibleValue);
		} catch (Exception e) {
			throw new Exception("Unable to select item: '" + visibleValue + "' in list: '" + list);
		}
	}
	
	//Select LOV value based on displayed text (such as January in example above)
	//Use Example:   ListControl.selectByValue(lstFromMonth, "3");
	static public void selectByValue(WebElement list, String value) throws Exception {
		try {
			Select dropdown = new Select(list); 
			dropdown.selectByValue(value);
		} catch (Exception e) {
			throw new Exception("Unable to select item with DOM value: '" + value + "' in list: '" + list, e);
		}
	}
	
	
}