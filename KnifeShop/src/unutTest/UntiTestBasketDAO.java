package unutTest;

import org.junit.Test;
import exceptions.MYSQLException;
import model.dao.DBBasketDAO;
import static org.junit.Assert.*;

public class UntiTestBasketDAO {

	@Test
	public void testAddKnifeInBasket(){
		
	}
	
	@Test
	public void testIsSuchKnifeInBasket() throws MYSQLException{
		boolean result = DBBasketDAO.getInstance().isThereSuchKnifeInTheBasketByKnifeId(11111, 111);
		boolean expected = false;
		assertEquals(expected, result);
	}

}
