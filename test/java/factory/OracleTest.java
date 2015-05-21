package factory;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.*;

import static org.junit.Assert.*;

/**
 * Created by Dell on 5/19/2015.
 */
public class OracleTest {
    private static AbstractFactory dialectFactory;
    private static Dialect dialect;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dialectFactory = AbstractFactory.getFactory(AbstractFactory.DIALECT);
        dialect = dialectFactory.getDialect(DialectFactory.ORACLE);
    }

    @Test
    public void testGetEmployeeRepositorySuccessfully(){
        EmployeeRepository employeeRepository = dialect.getEmployeeRepository();
        assertNotNull("Instanta employeeRepository intoarsa de metoda este nula",employeeRepository);
    }

    @Test
    public void testGetEmployeeRepositoryInstanceSuccessfully(){
        EmployeeRepository employeeRepository = dialect.getEmployeeRepository();
        assertTrue("Instanta employeeRepository intoarsa de metoda nu este cea corecta", employeeRepository instanceof OracleEmployeeRepository);
    }

    @Test
    public void testGetDepartmentRepositorySuccessfully(){
        DepartmentRepository departmentRepository = dialect.getDepartmentRepository();
        assertNotNull("Instanta departmentRepository intoarsa de metoda este nula",departmentRepository);
    }

    @Test
    public void testGetDepartmentRepositoryInstanceSuccessfully(){
        DepartmentRepository departmentRepository = dialect.getDepartmentRepository();
        assertTrue("Instanta departmentRepository intoarsa de metoda nu este cea corecta", departmentRepository instanceof OracleDepartmentRepository);
    }

    @Test
    public void testGetIstoricRepositorySuccessfully(){
        IstoricRepository istoricRepository = dialect.getIstoricRepository();
        assertNotNull("Instanta istoricRepository intoarsa de metoda este nula",istoricRepository);
    }

    @Test
    public void testGetIstoricRepositoryInstanceSuccessfully(){
        IstoricRepository istoricRepository = dialect.getIstoricRepository();
        assertNotNull("Instanta istoricRepository intoarsa de metoda nu este cea corecta",istoricRepository instanceof OracleIstoricRepository);
    }
}
