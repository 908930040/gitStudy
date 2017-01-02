package springMybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Person;
import com.yc.dao.BaseDao;
import com.yc.dao.PersonDao;

import junit.framework.TestCase;

public class TestConnection extends TestCase {

	public void testDataSource() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

		assertNotNull(ac.getBean("dataSource"));
		System.out.println(ac.getBean("dataSource"));
		DataSource ds = (DataSource) ac.getBean("dataSource");

		Connection con = ds.getConnection();
		System.out.println(con);
	}

	public void testPersonDaoMybatisImpl() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

		assertNotNull(ac.getBean("personDaoMybatisImpl"));
		System.out.println(ac.getBean("personDaoMybatisImpl"));

		PersonDao pd = (PersonDao) ac.getBean("personDaoMybatisImpl");
		List<Person> list = pd.findAll();

		for (Person p : list) {
			System.out.println(p);
		}

	}

	//测试二级缓存 
	public void testPersonDaoMybatisImpl2() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

		assertNotNull(ac.getBean("personDaoMybatisImpl"));
		System.out.println(ac.getBean("personDaoMybatisImpl"));

		PersonDao pd = (PersonDao) ac.getBean("personDaoMybatisImpl");
		List<Person> list = pd.findAll();
		for (Person p : list) {
			System.out.println(p);
		}

		list = pd.findAll();
		for (Person p : list) {
			System.out.println(p);
		}
	}
	
	//测试  baseDaoImpl
	public void testbaseDaoMybatisImpl() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

		Person person=new Person();
		BaseDao<Person> pd=(BaseDao<Person>)ac.getBean("baseDaoMybatisImpl");
		System.out.println( pd );
		List<Person> list = pd.findAll( person,"findAll");

		for (Person p : list) {
			System.out.println(p);
		}

	}
}
