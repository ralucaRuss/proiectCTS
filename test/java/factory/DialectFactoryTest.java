package factory;

import exceptions.FactoryNotFoundException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dell on 5/19/2015.
 */
public class DialectFactoryTest extends TestCase{
    private AbstractFactory dialectFactory;
    public DialectFactoryTest(String numeMetoda){
        super(numeMetoda);
    }
    @Before
    public void setUp()throws Exception {
        dialectFactory = AbstractFactory.getFactory(AbstractFactory.DIALECT);
    }

    @Test
     public void testGetOracleDialectSuccessfully() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(1);
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof Oracle);
    }

    @Test
    public void testGetMySqlDialectSuccessfully() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(2);
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof MySql);
    }

    @Test
    public void testGetOracleDialectSuccessfullyWithConstant() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(DialectFactory.ORACLE);
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof Oracle);
    }

    @Test
    public void testGetMySqlDialectSuccessfullyWithConstant() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(DialectFactory.MYSQL);
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof MySql);
    }

    @Test
    public void testGetOracleDialectSuccessfullyWithNonPrimitiveValue() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(new Integer(1));
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof Oracle);
    }

    @Test
    public void testGetMySqlDialectSuccessfullyWithNonPrimitiveValue() throws FactoryNotFoundException {
        Dialect dialect = dialectFactory.getDialect(new Integer(2));
        assertTrue("Instanta obtinuta nu este de tipul Oracle",dialect instanceof MySql);
    }

    @Test
    public void testGetOracleDialectWithWrongValue(){
        try {
            Dialect dialect = dialectFactory.getDialect(3);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetDialectWithWrongValue(){
        try {
            Dialect dialect = dialectFactory.getDialect(100);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetDialectWithWrongMinValue(){
        try {
            Dialect dialect = dialectFactory.getDialect(Integer.MIN_VALUE);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetDialectWithWrongMaxValue(){
        try {
            Dialect dialect = dialectFactory.getDialect(Integer.MAX_VALUE);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }

    @Test
    public void testGetDialectWithWrongNegativeValue(){
        try {
            Dialect dialect = dialectFactory.getDialect(-1912);
            fail();
        }catch(FactoryNotFoundException ex){

        }
    }
}
