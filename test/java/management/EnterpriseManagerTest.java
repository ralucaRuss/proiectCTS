package management;

import exceptions.InconsistentDataException;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Dell on 5/20/2015.
 */
public class EnterpriseManagerTest {
    private List<Employee> empList ;
    private EnterpriseManager enterpriseManager = EnterpriseManager.getInstance();
    private static FileReader fileReader;
    private static BufferedReader bf;

    @Before
    public void setUp()throws Exception {
        empList = new ArrayList<Employee>();
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        fileReader = new FileReader("dateTest.txt");
        bf = new BufferedReader(fileReader);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        bf.close();
        fileReader.close();
    }

    @Test
    public void testEmployeeSalarySumWithPositiveValuesSuccess()throws Exception{
        String line = null;
        int  contor = 0;
        Employee e1 = mock(Employee.class);
        Employee e2 = mock(Employee.class);
        Employee e3 = mock(Employee.class);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        while ((line = bf.readLine()) != null && line.length() > 0 && contor<3) {
            when(empList.get(contor).getSalary()).thenReturn(new BigDecimal(line));
            contor++;
        }
        assertEquals("Suma salariilor nu este calculata corect",new BigDecimal(7932),enterpriseManager.employeeSalarySum(empList));
    }

    @Test(expected = InconsistentDataException.class)
    public void testEmployeeSalarySumWithAllNegativeValuesFail() throws Exception{
        String line = null;
        int  contor = 0;
        Employee e1 = mock(Employee.class);
        Employee e2 = mock(Employee.class);
        Employee e3 = mock(Employee.class);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        while ((line = bf.readLine()) != null && line.length() > 0 && contor<3) {
            when(empList.get(contor).getSalary()).thenReturn(new BigDecimal(line));
            contor++;
        }
        enterpriseManager.employeeSalarySum(empList);
    }

    @Test
    public void testEmployeeSalarySumWithZeroValuesInList() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(BigDecimal.ZERO);
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(1000));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(BigDecimal.ZERO);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        assertEquals("Suma trebuie sa fie egala cu 1000",new BigDecimal(1000),enterpriseManager.employeeSalarySum(empList));
    }

    @Test(expected = InconsistentDataException.class)
    public void testEmployeeSalarySumWithNegativeAndPositiveValuesFail() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(new BigDecimal(-1000));
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(4932));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(new BigDecimal(-2000));
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        enterpriseManager.employeeSalarySum(empList);
    }

    @Test
    public void testEmployeeSalarySumEmptyList() throws Exception{
        assertEquals("Suma trebuie sa fie egala cu 0",BigDecimal.ZERO,enterpriseManager.employeeSalarySum(empList));
    }

    @Test
    public void testEmployeeSalarySumNullList() throws Exception{
        assertEquals("Suma trebuie sa fie egala cu 0",BigDecimal.ZERO,enterpriseManager.employeeSalarySum(null));
    }

    @Test
    public void testEmployeeSalarySumNullValuesInList() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(null);
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(null);
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(null);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        assertEquals("Suma trebuie sa fie egala cu 0",BigDecimal.ZERO,enterpriseManager.employeeSalarySum(null));
    }

    @Test
    public void testEmployeeSalarySumNullAndNotNullValuesInList() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(null);
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(1000));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(null);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        assertEquals("Suma trebuie sa fie egala cu 1000",new BigDecimal(1000),enterpriseManager.employeeSalarySum(empList));
    }

    @Test
    public void testMaxSalaryWithSuccess() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(new BigDecimal(4500));
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(1000));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(new BigDecimal(4799));
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        assertEquals("Maximul trebuie sa fie egal cu 4799",new BigDecimal(4799),enterpriseManager.maxSalary(empList));
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryWithAllNegativeValuesFail() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(new BigDecimal(-1000));
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(-4932));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(new BigDecimal(-2000));
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        enterpriseManager.maxSalary(empList);
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryWithPositiveAndNegativeValuesFail() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(new BigDecimal(-1000));
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(4932));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(new BigDecimal(-2000));
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        enterpriseManager.maxSalary(empList);
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryNullList() throws Exception{
        enterpriseManager.maxSalary(null);
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryAllNullValuesInList() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(null);
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(null);
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(null);
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        enterpriseManager.maxSalary(empList);
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryWithNullAndNotNullValuesInList() throws Exception{
        Employee e1 = mock(Employee.class);
        when(e1.getSalary()).thenReturn(null);
        Employee e2 = mock(Employee.class);
        when(e2.getSalary()).thenReturn(new BigDecimal(3000));
        Employee e3 = mock(Employee.class);
        when(e3.getSalary()).thenReturn(new BigDecimal(3500));
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        enterpriseManager.maxSalary(empList);
    }

    @Test(expected = InconsistentDataException.class)
    public void testMaxSalaryWithEmptyList() throws Exception{
        enterpriseManager.maxSalary(empList);
    }


}
