package olaf.cafe.omami;

import org.junit.Assert;
import org.junit.Test;

public class ExampleTests {

	@Test
	public void olafShouldNotBeAnia() {
		Assert.assertNotEquals("Olaf", "Ania");
	}
}
