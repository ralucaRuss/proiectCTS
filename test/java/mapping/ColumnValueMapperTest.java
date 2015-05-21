package mapping;

import annotations.Column;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import pojo.Employee;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Dell on 5/19/2015.
 */
public class ColumnValueMapperTest extends TestCase {
    private ColumnValueMapper<Employee> cvm;

    public ColumnValueMapperTest(String numeMetoda){
        super(numeMetoda);
    }

    @Before
    public void setUp()throws Exception {
        cvm = new ColumnValueMapper<Employee>();
    }

    @Test
    public void testGetColumnNameSuccessfully() throws Exception{
        Class employee = Employee.class;
        Field field = employee.getDeclaredField("name");
        Column column = mock(Column.class);
        when(column.name()).thenReturn("name");
        assertEquals("Metoda nu returneaza numele coloanei din baza de date", "name", cvm.getColumnName(field));
    }

    @Test
    public void testGetColumnNameWithNullValue()throws Exception{
        Column column = mock(Column.class);
        when(column.name()).thenReturn("name");
        try{
           cvm.getColumnName(null);
           fail();
        }catch(NullPointerException ex){

        }
    }

    @Test
    public void testGetColumnNameWhenNoAnnotationIsPresent()throws Exception{
        Class employee = Employee.class;
        Field field = employee.getDeclaredField("testEmployee");
        Column column = mock(Column.class);
        when(column.name()).thenReturn("testEmployee");
        assertNull("Metoda trebuie sa returneze valoare nula pentru ca acel camp nu exista in baza de date",cvm.getColumnName(field));
    }

    @Test
    public void testColumnValueMappingSuccessfully() throws Exception{
        Class employee = Employee.class;
        Field name = employee.getDeclaredField("name");
        Field age = employee.getDeclaredField("age");
        Field [] fields = new Field[2];
        fields[0] = name;
        fields[1] = age;
        Map<String,Object> map = cvm.columnValueMapping(fields,new Employee());
        Map<String,Object> rezAsteptat = new HashMap<String, Object>();
        rezAsteptat.put("name",name.get(new Employee()));
        rezAsteptat.put("age",age.get(new Employee()));
        assertEquals("Campurile entitatii nu sunt mapate corespunzator",rezAsteptat,map);
    }

    @Test
    public void testColumnValueMappingWithNullParameter() throws Exception{
        Map<String,Object> map = cvm.columnValueMapping(null,new Employee());
        assertTrue("Colectia returnata trebuie sa fie goala",map.isEmpty());
    }

    @Test
    public void testColumnValueMappingWithNullValues() throws Exception{
        Field [] fields = new Field[2];
        fields[0] = null;
        fields[1] = null;
        Map<String,Object> map = cvm.columnValueMapping(fields,new Employee());
        assertTrue("Colectia returnata trebuie sa fie goala",map.isEmpty());
    }

    @Test
    public void testColumnValueMappingWithNullValuesAndNotNullValues() throws Exception{
        Class employee = Employee.class;
        Field name = employee.getDeclaredField("name");
        Field [] fields = new Field[2];
        fields[0] = name;
        fields[1] = null;
        Map<String,Object> map = cvm.columnValueMapping(fields,new Employee());
        Map<String,Object> rezAsteptat = new HashMap<String, Object>();
        rezAsteptat.put("name",name.get(new Employee()));
        assertEquals("Campurile entitatii nu sunt mapate corespunzator",rezAsteptat,map);
    }

    @Test
    public void testColumnValueMappingWithEmptyFieldArray() throws Exception{
        Field [] fields = new Field[2];
        Map<String,Object> map = cvm.columnValueMapping(fields,new Employee());
        assertTrue("Colectia returnata trebuie sa fie goala",map.isEmpty());
    }
}
