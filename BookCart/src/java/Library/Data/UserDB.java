package Library.Data;

import java.sql.*;
import Library.Models.*;
import Library.Utility.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
public class UserDB {

    public static void insert(User user) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO User (FirstName,MiddleName, LastName, EmailId,dob,password,salt)"
                + "VALUES (?, ?, ?,?,?,?,? )";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmailId());
            java.sql.Date date = new java.sql.Date(user.getDOB().getTime());
            ps.setDate(5,date);
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getSalt());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void provideAdminPrivilege(String emailid,int admin) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query="";
        if(admin==1)
        {
        query = "UPDATE User SET admin = true where emailid = ?";
        }
        else
        {
        query = "UPDATE User SET admin = false where emailid = ?";  
        }
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailid);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void resetPassword(String username,String password,String salt) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE User SET password=?,salt=? where emailid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, salt);
            ps.setString(3, username);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User selectUser(User user) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String searchQuery =
               "select * from user where EmailId=?";
        
        try {
            ps = connection.prepareStatement(searchQuery);
            ps.setString(1, user.getEmailId());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setAdmin(rs.getBoolean("Admin"));
                user.setDOB(rs.getDate("dob"));
                user.setEmailId(rs.getString("EmailId"));
                user.setFirstName(rs.getString("FirstName"));
                user.setMiddleName(rs.getString("MiddleName"));
                user.setLastName(rs.getString("LastName"));
                user.setIsAvailable(true);
            }
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<User> selectUsers() throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        String query = "SELECT * FROM User";
        
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next())
            {
                User user = new User();
                user.setEmailId(rs.getString("EmailId"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setAdmin(rs.getBoolean("admin"));
                users.add((user));
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean loginValidation(String email,String password) throws Exception{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String salt ="";
        String DBpassword ="";
        String query = "SELECT salt,password FROM User "
                + "WHERE EmailId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next())
            {
                salt=rs.getString("salt");
                DBpassword=rs.getString("password");
            }
        if(salt!="")
        {
            String pass = Utility.hashAndSaltPassword(password, salt);
            if(pass.equals(DBpassword))
            {
                return true;
            }
            else
                return false;
        }
        return false;
        }
        catch (SQLException e) {
            System.err.println(e);
            throw new Exception(e.getMessage());
        }
        catch (NoSuchAlgorithmException n){
            System.err.println(n);
            throw new Exception(n.getMessage());
        }
        finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}