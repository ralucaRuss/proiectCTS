package testsuites;

import connection.ConnectionManagerTest;
import factory.AbstractFactoryTest;
import factory.DialectFactoryTest;
import factory.OracleTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import management.EnterpriseManager;
import management.EnterpriseManagerTest;
import mapping.ColumnValueMapperTest;

/**
 * Created by Dell on 5/20/2015.
 */
public class SelectedTests extends TestSuite{
   public static Test suite(){
       TestSuite testSuite = new TestSuite();
       testSuite.addTest(new ConnectionManagerTest("testGetDataSourceConnectSuccessfully"));
       testSuite.addTest(new AbstractFactoryTest("testGetFactorySuccessfullyWithConstant"));
       testSuite.addTest(new DialectFactoryTest("testGetOracleDialectSuccessfully"));
       testSuite.addTest(new ColumnValueMapperTest("testGetColumnNameSuccessfully"));
       testSuite.addTest(new ColumnValueMapperTest("testColumnValueMappingSuccessfully"));
       return testSuite;
   }
}
