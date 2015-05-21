package metadata;

import exceptions.NullDatabaseSchemaException;
import exceptions.NullDatabaseTableException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DbMetadataManagerTest {
    private static DbMetadataManager dbMetadataManager;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbMetadataManager = new DbMetadataManagerImpl();
    }

    @Test
    public void testGetDatabaseProductName() throws Exception {
        String productName = dbMetadataManager.getDatabaseProductName();
        Assert.assertEquals("Numele SGBD-ului nu este corespunzator", "Oracle", productName);
    }

    @Test
    public void testGetDatabaseProductVersion() throws Exception {
        String productVersion = dbMetadataManager.getDatabaseProductVersion();
        Assert.assertTrue("Versiunea bazei de date nu este corespunzatoare", productVersion.contains("11g"));
        System.out.println("Product Version: "+productVersion);
    }

    @Test
    public void testGetDatabaseDriverVersion() throws Exception {
        String driverVersion = dbMetadataManager.getDatabaseDriverVersion();
        Assert.assertNotNull("Versiunea driverului nu poate fi nula",driverVersion);
        System.out.println("Driver Version: "+driverVersion);
    }

    @Test
    public void testGetDatabaseUserName() throws Exception {
        String userName = dbMetadataManager.getDatabaseUserName();
        Assert.assertTrue("User-ul bazei de date ne este corect", userName.equalsIgnoreCase("russindilarrr"));
        System.out.println("User Name: " + userName);
    }

    @Test
    public void testGetDatabaseTablesFromSchemaSuccessfully() throws Exception {
        Map<String, String> tables = new HashMap<String, String>();
        tables = dbMetadataManager.getDatabaseTablesFromSchema("RUSSINDILARRR");
        Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("EMPLOYEE","TABLE");
        expectedMap.put("DEPARTMENT","TABLE");
        expectedMap.put("ISTORIC","TABLE");
        Assert.assertEquals("Tabelele nu corespund schemei cerute",expectedMap,tables);
        for(String key: tables.keySet())
            System.out.println("Table Name: "+key+" Table Type: "+tables.get(key));
    }

    @Test
    public void testGetDatabaseTablesFromUnexistentSchema() throws Exception {
        Map<String, String> tables = new HashMap<String, String>();
        tables = dbMetadataManager.getDatabaseTablesFromSchema("RANDOMSCHEMA");
        assertTrue("In aceasta schema nu ar trebui sa existe tabele", tables.isEmpty());
    }

    @Test
    public void testGetDatabaseTablesFromNullSchema() throws Exception {
        Map<String, String> tables = new HashMap<String, String>();
        try {
            tables = dbMetadataManager.getDatabaseTablesFromSchema(null);
            fail();
        }catch(NullDatabaseSchemaException ex){

        }
    }

    @Test
      public void testGetTableColumnsSuccessfully() throws Exception {
        Map<String, Integer> columns = new HashMap<String, Integer>();
        columns = dbMetadataManager.getTableColumns("EMPLOYEE");
        assertTrue("Coloanele tabelei nu sunt returnate corespunzator",columns.keySet().contains("SALARY"));
    }

    @Test
    public void testGetTableColumnsForUnexistentTable() throws Exception {
        Map<String, Integer> columns = new HashMap<String, Integer>();
        columns = dbMetadataManager.getTableColumns("HISTORY");
        assertTrue("Nu exista coloane pentru aceasta tabela",columns.isEmpty());
    }

    @Test
    public void testGetTableColumnsForNullParameter() throws Exception {
        Map<String, Integer> columns = new HashMap<String, Integer>();
        try {
            columns = dbMetadataManager.getTableColumns(null);
            fail();
        }catch(NullDatabaseTableException ex){

        }
    }

    @Test
    public void testGetDatabaseSchemas() throws Exception {
        List<String> schemas = new ArrayList<String>();
        schemas = dbMetadataManager.getDatabaseSchemas();
        assertTrue("Database schemas are not correct", schemas.contains("RUSSINDILARRR"));
    }

    @Test
     public void testGetTablePrimaryKeySuccessfully() throws Exception {
        Map<String, String> primaryKey = new HashMap<String, String>();
        primaryKey = dbMetadataManager.getTablePrimaryKey("EMPLOYEE");
        assertTrue("The primary key is not correct", primaryKey.keySet().contains("EMPLOYEE_ID"));
    }

    @Test
    public void testGetTablePrimaryKeyForUnexistentTable() throws Exception {
        Map<String, String> primaryKey = new HashMap<String, String>();
        primaryKey = dbMetadataManager.getTablePrimaryKey("RANDOM");
        assertTrue("The primary key should not exist", primaryKey.isEmpty());
    }

    @Test
    public void testGetTablePrimaryKeyForNullTableName() throws Exception {
        Map<String, String> primaryKey = new HashMap<String, String>();
        try {
            primaryKey = dbMetadataManager.getTablePrimaryKey(null);
            fail();
        }catch(NullDatabaseTableException ex){

        }
    }

    @Test
    public void testTableExportedKeysSuccessfully() throws Exception {
        Map<String,String> exportedKeys = new HashMap<String, String>();
        exportedKeys = dbMetadataManager.getTableExportedKeys("DEPARTMENT");
        assertTrue("Exported keys are not correct", exportedKeys.get("EMPLOYEE").equals("DEPARTMENT_ID"));
    }

    @Test
    public void testTableExportedKeysForUnexistentTable() throws Exception {
        Map<String,String> exportedKeys = new HashMap<String, String>();
        exportedKeys = dbMetadataManager.getTableExportedKeys("RANDOM");
        assertTrue("Exported keys should not exist", exportedKeys.isEmpty());
    }

    @Test
    public void testTableExportedKeysForNullTableName() throws Exception {
        Map<String,String> exportedKeys = new HashMap<String, String>();
        try {
            exportedKeys = dbMetadataManager.getTableExportedKeys(null);
            fail();
        }catch(NullDatabaseTableException ex){

        }
    }

    @Test
    public void testTableImportedKeysSuccessfully() throws Exception {
        Map<String,String> importedKeys = new HashMap<String, String>();
        importedKeys = dbMetadataManager.getTableImportedKeys("EMPLOYEE");
        assertTrue("Imported keys are not correct", importedKeys.get("DEPARTMENT").equals("DEPARTMENT_ID"));
    }

    @Test
    public void testTableImportedKeysForUnexistentTable() throws Exception {
        Map<String,String> importedKeys = new HashMap<String, String>();
        importedKeys = dbMetadataManager.getTableImportedKeys("RANDOM");
        assertTrue("Imported keys should not exist", importedKeys.isEmpty());
    }

    @Test
    public void testTableImportedKeysForNullTableName() throws Exception {
        Map<String,String> importedKeys = new HashMap<String, String>();
        try {
            importedKeys = dbMetadataManager.getTableImportedKeys(null);
            fail();
        }catch(NullDatabaseTableException ex){

        }
    }
}