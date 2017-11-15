package Task9.tests;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest extends BaseTest{

    @Test
    public void AddAndRemoveProducts(){
        for (int i = 0; i <3 ; i++) {
            app.addProductToCart();
        }
        app.removeAllProducts();

        Assert.assertFalse("The products are still present on the page", app.isProductTablePresent());
    }

}
