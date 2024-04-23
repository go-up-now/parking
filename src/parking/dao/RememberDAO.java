
package parking.dao;

import parking.entity.Remember;
import parking.utils.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class RememberDAO implements ParkDAO<Remember, Object>{
     String insert_sql = "INSERT INTO Remember(userName, pass) VALUES(?,?)";
    String update_sql = "UPDATE Remember SET pass = ? WHERE userName= ?";
    String delete_sql = "delete remember";
    String selectALL_sql = "select * from remember";
    String selectByID = "select * from remember where userName=?";

    @Override
    public void insert(Remember e) {
         try {
             JdbcHelper.executeUpdate(insert_sql, e.getUser(), e.getPass());
         } catch (SQLException ex) {
             Logger.getLogger(RememberDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void update(Remember e) {
        try {
             JdbcHelper.executeUpdate(update_sql, e.getPass(), e.getUser());
         } catch (SQLException ex) {
             Logger.getLogger(RememberDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void delete(Object id) {
        try {
             JdbcHelper.executeUpdate(delete_sql);
         } catch (SQLException ex) {
             Logger.getLogger(RememberDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @Override
    public List<Remember> selectALL() {
        return selectBySQL(selectALL_sql);
    }

    @Override
    public Remember selectByID(Object id) {
        List<Remember> list = selectBySQL(selectByID, id);
        return list.isEmpty()? null:list.get(0);
    }

    @Override
    public List<Remember> selectBySQL(String sql, Object... args) {
        List<Remember> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.ExecuteQuery(sql, args);
            while(rs.next()){
                Remember rmb = new Remember();
                rmb.setUser((String) rs.getObject("userName"));
                rmb.setPass((String) rs.getObject("pass"));
                list.add(rmb);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
//    public void deleteALL(Object id) {
//        try {
//             Xjdbc.update(delete_sql, id);
//         } catch (SQLException ex) {
//             Logger.getLogger(RememberDAO.class.getName()).log(Level.SEVERE, null, ex);
//         }
//    }

}
