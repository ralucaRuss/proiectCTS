package factory;

import exceptions.FactoryNotFoundException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dell on 5/19/2015.
 */
public class AbstractFactoryTest extends TestCase{
    public AbstractFactoryTest(String numeMetoda){
        super(numeMetoda);
    }
    @Test
    public void testGetFactorySuccessfully() throws FactoryNotFoundException{
        AbstractFactory dialectFactory = AbstractFactory.getFactory(1);
        assertTrue("Instanta obtinuta nu este de tipul DialectFactory",dialectFactory instanceof DialectFactory);
    }

    @Test
    public void testGetFactorySuccessfullyWithConstant() throws FactoryNotFoundException{
        AbstractFactory dialectFactory = AbstractFactory.getFactory(AbstractFactory.DIALECT);
        assertTrue("Instanta obtinuta nu este de tipul DialectFactory",dialectFactory instanceof DialectFactory);
    }

    @Test
    public void testGetFactorySuccessfullyWithNonPrimitiveValue() throws FactoryNotFoundException{
        AbstractFactory dialectFactory = AbstractFactory.getFactory(new Integer(1));
        assertTrue("Instanta obtinuta nu este de tipul DialectFactory",dialectFactory instanceof DialectFactory);
    }

    @Test
    public void testGetFactoryWithWrongValue(){
            try {
                AbstractFactory dialectFactory = AbstractFactory.getFactory(2);
                fail();
            }catch(FactoryNotFoundException ex){

            }
    }

    @Test
    public void testGetFactoryWithWrongMinValueInteger(){
        try {
            AbstractFactory dialectFactory = AbstractFactory.getFactory(Integer.MIN_VALUE);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetFactoryWithWrongMaxValueInteger(){
        try {
            AbstractFactory dialectFactory = AbstractFactory.getFactory(Integer.MAX_VALUE);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetFactoryWithWrongNegativeValue(){
        try {
            AbstractFactory dialectFactory = AbstractFactory.getFactory(-100);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }
}
