package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoOptions {
    public Connection conn = MyAccountDAO.getConnection();
    public boolean getInfo(){
        String select = "select * from My_Accounts where UserName=?";
        try{
            PreparedStatement slt = conn.prepareStatement(select);
//            slt.setString(1, bookinfo.getBookname());
            ResultSet rs = slt.executeQuery();
            while(rs.next()){//这里不加rs.next()不会查询下一列
//                bookinfo.setPrice(rs.getDouble(2));
//                bookinfo.setDiscount(rs.getDouble(3));
            }
            rs.close();
            //conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void saveBookInfo(){
        Connection conn = MyAccountDAO.getConnection();
        String insert = "insert into books(bookname,price,discount) value(?,?,?)";
        try {
            PreparedStatement ist = conn.prepareStatement(insert);
//            ist.setString(1,book.getBookname());
//            ist.setDouble(2,book.getPrice());
//            ist.setDouble(3,book.getDiscount());
            ist.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editBookInfo(){
        Connection conn = MyAccountDAO.getConnection();
        String update = "update books set bookname=replac(?,?,?)";
        try{
            PreparedStatement upt=conn.prepareStatement(update);
//            upt.setString(1,book.getBookname());
//            upt.setDouble(2,book.getPrice());
//            upt.setDouble(3,book.getDiscount());
            upt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBook(){
        Connection conn = MyAccountDAO.getConnection();
        String delete = "delete from books where bookname=?";
        try {
            PreparedStatement ps=conn.prepareStatement(delete);
//            ps.setString(1,book.getBookname());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
