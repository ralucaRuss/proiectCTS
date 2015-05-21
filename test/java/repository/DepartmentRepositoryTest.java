package repository;

import exceptions.UnexistentSQLDataException;
import factory.AbstractFactory;
import factory.Dialect;
import factory.DialectFactory;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.Null;
import pojo.Department;

import java.math.BigDecimal;
import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DepartmentRepositoryTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private static AbstractFactory dialectFactory;
    private static Dialect dialect;
    private static DepartmentRepository departmentRepository;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dialectFactory = AbstractFactory.getFactory(AbstractFactory.DIALECT);
        dialect = dialectFactory.getDialect(DialectFactory.ORACLE);
        departmentRepository = dialect.getDepartmentRepository();
    }

    @Test
    public void testFindAll() throws Exception {
        List<Department> departamente = departmentRepository.findAll();
        for(Department d: departamente)
            System.out.println(d);
        assertTrue("Lista departamentelor nu trebuie sa fie nula", departamente != null);
    }

    @Test
    public void testFindAllWithPaging() throws Exception {
        List<Department> departamente = departmentRepository.findAllWithPaging(2,2); //first row, row count
        assertEquals("Interogarea trebuie sa intoarca doua inregistrari", 2, departamente.size());
    }

    @Test
    public void testFindByIdSuccessfully() throws Exception {
        Department d = departmentRepository.findById(new BigDecimal(11));
        assertEquals(new BigDecimal(11), d.getId());
    }

    @Test
    public void testFindByIdWithNullId() throws Exception {
        try {
            Department d = departmentRepository.findById(null);
            fail();
        }catch(UnexistentSQLDataException ex){

        }
    }

    @Test
    public void testFindByQuerySuccessfully() throws Exception {
        String query = "SELECT * FROM Department WHERE department_id = ? and name = ?";
        List<Object> params = new ArrayList<Object>();
        params.add("11");
        params.add("Department2");
        List<Department> departments = departmentRepository.findByQuery(query,params);
        Assert.assertTrue("Query-ul specificat nu este corect", departments.get(0).getName().equals("Department2"));
    }

    @Test(expected = UnexistentSQLDataException.class)
    public void testFindByQueryNullQuery() throws Exception {
        List<Object> params = new ArrayList<Object>();
        params.add("11");
        params.add("Department2");
        List<Department> departments = departmentRepository.findByQuery(null, params);
    }

    @Test(expected = UnexistentSQLDataException.class)
    public void testFindByQueryWithNullParams() throws Exception {
        String query = "SELECT * FROM Department WHERE department_id = ? and name = ?";
        List<Department> departments = departmentRepository.findByQuery(query, null);
    }

    @Test(expected = IllegalStateException.class)
    public void testFindByQueryWithWrongQuery() throws Exception {
        String query = "UPDATE Department SET name=? WHERE department_id=?";
        List<Object> params = new ArrayList<Object>();
        params.add("Test");
        params.add("11");
        List<Department> departments = departmentRepository.findByQuery(query, params);
    }

    @Test
    public void testFindByQueryWithEmptyParamList() throws Exception {
        String query = "Select * FROM Department";
        List<Object> params = new ArrayList<Object>();
        List<Department> departments = departmentRepository.findByQuery(query, params);
        assertTrue("Trebuie sa fie returnate toate departamentele", departments.size()==4);
    }

    @Test(expected = IllegalStateException.class)
    public void testFindByQueryWithWrongNumberOfParams() throws Exception {
        String query = "SELECT * FROM Department WHERE department_id = ? and name = ?";
        OracleGenericRepository<Department> oracleGenericRepository = mock(OracleGenericRepository.class);
        when(oracleGenericRepository.numberOfParameters(query)).thenReturn(2);
        List<Object> params = new ArrayList<Object>();
        params.add("Test");
        params.add("11");
        params.add("Ralu");
        List<Department> departments = departmentRepository.findByQuery(query, params);
    }

    @Test
    public void testUpdate() throws Exception {
        Department d = departmentRepository.findById(new BigDecimal(11));
        d.setName("Department2");
        int nr = departmentRepository.update(d);
        Assert.assertTrue(nr!=0);
    }

    @Test
    public void testFindDepartmentByNameSuccessfully() throws Exception {
        List<Department> departments = departmentRepository.findDepartmentByName("Contabilitate");
        Assert.assertTrue("Departamentul trebuie sa existe in baza de date",departments.get(0).getName().equalsIgnoreCase("Contabilitate"));
    }

    @Test(expected = UnexistentSQLDataException.class)
    public void testFindDepartmentByNameWithNullName() throws Exception {
        List<Department> departments = departmentRepository.findDepartmentByName(null);
    }

    @Test
    public void testFindDepartmentByDateSuccessfully() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date d = sdf.parse("01-SEP-2014");
        List<Department> departments = departmentRepository.findDepartmentByDate(d);
        Assert.assertTrue("Nu sunt returnate departamentele care indeplinesc conditia", departments.size()==2);
    }

    @Test(expected = UnexistentSQLDataException.class)
    public void testFindDepartmentByDateWithNullDate() throws Exception {
        List<Department> departments = departmentRepository.findDepartmentByDate(null);
    }
    
}