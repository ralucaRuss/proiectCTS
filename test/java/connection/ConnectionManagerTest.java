package connection;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionManagerTest extends TestCase{
    private ConnectionManager connectionManager;
    private Connection con;

    public ConnectionManagerTest(String numeMetoda){
        super(numeMetoda);
    }

    @Before
    public void setUp()throws Exception {
        connectionManager = new ConnectionManager();
    }

    @Test
    public void testGetDataSourceConnectSuccessfully() throws Exception {
        con = connectionManager.getDataSource().getConnection(); //se obtine o conexiune din pool-ul de conexiuni
        assertTrue("Nu s-a putut realiza conexiunea la baza de date", !con.isClosed());
    }

    @Test
    public void testGetDataSourceWithException() {

        try {
            con = connectionManager.getDataSource().getConnection("random", "random");
            fail();
        } catch (SQLException ex) {

        }
    }

    @After
    public void tearDown() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}