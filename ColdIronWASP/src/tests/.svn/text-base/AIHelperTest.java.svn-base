package tests;

import java.util.Iterator;


import org.json.JSONException;
import org.junit.*;

import ai.helper.AIHelper;

public class AIHelperTest {

	@Test
	public void testGetBestUnitType() throws JSONException, Exception{
		
		AIHelper aiHelper = new AIHelper();
		String result = null;
		result = aiHelper.getBestUnitType(1000, 1000, 1000, 70);
		Assert.assertEquals("limit not considered", result, "noUnitPossible");
		result = aiHelper.getBestUnitType(9, 0, 0, 0);
		Assert.assertEquals("not enough wood for any unit not considered", "noUnitPossible", result);
		result = aiHelper.getBestUnitType(10, 0, 0, 0);
		Assert.assertEquals("unable to suggest the expected ", "Peon:1:1:5:20", result);
		result = aiHelper.getBestUnitType(20, 0, 0, 0);
		Assert.assertEquals("unable to suggest the expected ", "Swordsman:1:1:10:90", result);
		result = aiHelper.getBestUnitType(60, 0, 40, 0);
		// would be enough for "Swordsman:1:3:30:270" or "Swordsman:2:2:40:840"
		Assert.assertEquals("unable to suggest the expected ", "Swordsman:2:2:40:840", result);
		result = aiHelper.getBestUnitType(60, 0, 50, 0);
		// would be enough for "Swordsman:1:3:30:270" or "Swordsman:2:2:40:840"
		// or "Swordsman:3:1:40:900"
		Assert.assertEquals("unable to suggest the expected ", "Swordsman:3:1:40:900", result);
		
		// here you might try out different parameters :)
		System.out.println(aiHelper.getBestUnitType(1000, 500, 1300, 30));
		
		}
	
	
}
