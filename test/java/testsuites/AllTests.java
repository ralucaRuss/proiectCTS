package testsuites;

import connection.ConnectionManagerTest;
import factory.AbstractFactory;
import factory.AbstractFactoryTest;
import factory.DialectFactoryTest;
import factory.OracleTest;
import management.EnterpriseManagerTest;
import mapping.ColumnValueMapperTest;
import metadata.DbMetadataManagerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repository.DepartmentRepositoryTest;

/**
 * Created by Dell on 5/20/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ConnectionManagerTest.class, AbstractFactoryTest.class, DialectFactoryTest.class,OracleTest.class,
        EnterpriseManagerTest.class, ColumnValueMapperTest.class, DbMetadataManagerTest.class, DepartmentRepositoryTest.class})
public class AllTests {
}
