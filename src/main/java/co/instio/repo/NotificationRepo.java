package co.instio.repo;


import co.instio.entity.Details;
import co.instio.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.*;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class NotificationRepo {

     private final JdbcTemplate jdbcTemplate;

     private static  class NotificationDetails implements RowMapper<Details>{

         @Override
         public  Details mapRow(ResultSet rs, int rowNum) throws SQLException{
             Details details=new Details();
             details.setId(rs.getLong("id"));
             details.setDate(rs.getDate("date"));
             details.setMessage(rs.getString("message"));
             details.setSubject(rs.getString("subject"));
             details.setStatus(Status.valueOf(rs.getString("status")));
             return details;

         }
     }

     public List<Details>findAll(){
         String sql="SELECT * FROM details";
         return jdbcTemplate.query(sql,new NotificationDetails());
     }


     public Details findById(Long id){
         String sql= "SELECT * FROM details WHERE id=?";
         return jdbcTemplate.queryForObject(sql,new NotificationDetails(),id);
     }

     public int  save(Details details){
         String sql = "INSERT INTO details(date,subject,message) VALUES( ? ,? ,? ,?) ";
         return jdbcTemplate.update(sql,details.getDate(),details.getSubject(),details.getMessage(),details.getStatus().toString());
     }

     public int update(Long id,Details details){
         String sql= "UPDATE details SET message = ? , subject = ?  WHERE  id= ?";
         return  jdbcTemplate.update(sql,details.getMessage(),details.getSubject(),id);
     }

     public int deleteById(Long id){
         String sql="DELETE FROM  details WHERE id=?";
         return jdbcTemplate.update(sql,id);
     }
}
