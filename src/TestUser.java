import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestUser
{
    public static SessionFactory sessionFactory;
    static
    {
        try
        {
            Configuration config = new Configuration().configure();
            sessionFactory = config.buildSessionFactory();
        }
        catch (Exception e)
        {
            System.err.println("===================error===================");
            e.printStackTrace();
            System.err.println("===================error===================");
        } 
    }

    public void saveUser(User user)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
        public void findAll()
        {
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            try
            {
                tx = session.beginTransaction();
                Query query = session.createQuery("from User as u order by u.id asc");
                tx.commit();
                List<User> list = query.list();
                Iterator<User> it = list.iterator();
                System.out.println("append:" + list.size());
                while (it.hasNext())
                {
                    User user = it.next();
                    System.out.println("ID:" + user.getId());
                    System.out.println("Name:" + user.getName());
                    System.out.println("Pass:" + user.getPassword());
                }
            }
            catch (Exception e)
            {
                tx.rollback();
            }
            finally
            {
                session.close();
            }
        }

    @SuppressWarnings("unchecked")
        public User getUser(String User_id)
        {
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            User user = null;
            try
            {
                tx = session.beginTransaction();
                Query query = session.createQuery("from User where id = " + User_id);
                List<User> list = query.list();
                Iterator<User> it = list.iterator();
                while (it.hasNext())
                {
                    user = it.next();
                }
                tx.commit();
            }
            catch (Exception e)
            {
                tx.rollback();
            }
            finally
            {
                session.close();
            }
            return user;
        }

    public void loadUpdate(int User_id, String name)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            User user = (User) session.load(User.class, User_id);
            user.setName(name);
            tx.commit();
        }
        catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }

    public void delUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
    }

    public void test()
    {
          User user = new User();
          user.setName("≤‚ ‘");
          user.setPassword("123");
          this.saveUser(user);
        this.findAll();
        //  this.loadUpdate(user.getId(),"phop");
        //  User user = this.getUser("3");
        //  this.delUser(user);
    }

    public static void main(String[] args)
    {
        TestUser tu = new TestUser();
        tu.test();
        sessionFactory.close();
    }
}

