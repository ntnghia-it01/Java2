/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.java2.dao;

import com.fpoly.java2.config.DatabaseConnect;
import com.fpoly.java2.models.Statistical;
import com.fpoly.java2.models.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trongnghia
 */
public class StudentDAO {
    
    public List<Student> getList(){
        List<Student> students = new ArrayList<>();
        Connection conn = DatabaseConnect.connection();
        try{
            String sql = "SELECT * from students";
            PreparedStatement statment = conn.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            
            while(rs.next()){
//              Lấy dữ liệu từ db ra
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float labScore = rs.getFloat("lab_score");
                float quizScore = rs.getFloat("quiz_score");
                float assignmentScore = rs.getFloat("assignment_score");
                float finalExamScore = rs.getFloat("final_exam_score");
                float averageScore = rs.getFloat("average_score");
                String status = rs.getString("status");
                
//              Lưu dữ liệu đã lấy được vào đối tượng 
                Student student = new Student();
                student.setId(id);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                student.setPhone(phone);
                student.setLabScore(labScore);
                student.setQuizScore(quizScore);
                student.setAssignmentScore(assignmentScore);
                student.setFinalExamScore(finalExamScore);
                student.setAverageScore(averageScore);
                student.setStatus(status);
                
//              Thêm đối tượng vừa được lưu thông tin vào danh sách
                students.add(student);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return students;
    }
    
//  Tìm một sinh theo MSSV
//  Nếu có thì trả về thông tin đối tượng
//  Nếu không có thì trả về null
    public Student getByStudentCode(String studentCode){
        Connection conn = DatabaseConnect.connection();
        try{
            String sql = "SELECT * FROM students WHERE student_code=?";
            PreparedStatement statment = conn.prepareStatement(sql);
            statment.setString(1, studentCode);
            ResultSet rs = statment.executeQuery();
            
            while(rs.next()){
//              Lấy dữ liệu từ db ra
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float labScore = rs.getFloat("lab_score");
                float quizScore = rs.getFloat("quiz_score");
                float assignmentScore = rs.getFloat("assignment_score");
                float finalExamScore = rs.getFloat("final_exam_score");
                float averageScore = rs.getFloat("average_score");
                String status = rs.getString("status");
                
//              Lưu dữ liệu đã lấy được vào đối tượng 
                Student student = new Student();
                student.setId(id);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                student.setPhone(phone);
                student.setLabScore(labScore);
                student.setQuizScore(quizScore);
                student.setAssignmentScore(assignmentScore);
                student.setFinalExamScore(finalExamScore);
                student.setAverageScore(averageScore);
                student.setStatus(status);
                
                return student;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
//  Thêm sinh viên vào danh sách
    public boolean insertStudent(Student student){
        Connection conn = DatabaseConnect.connection();
        try{
            String insert = "INSERT INTO students"
                    + "(student_code, full_name, address, phone, lab_score, quiz_score, assignment_score, final_exam_score, average_score, status) VALUES"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statment = conn.prepareStatement(insert);
            
            statment.setString(1, student.getStudentCode());
            statment.setString(2, student.getFullName());
            statment.setString(3, student.getAddress());
            statment.setString(4, student.getPhone());
            statment.setFloat(5, student.getLabScore());
            statment.setFloat(6, student.getQuizScore());
            statment.setFloat(7, student.getAssignmentScore());
            statment.setFloat(8, student.getFinalExamScore());
            statment.setFloat(9, student.getAverageScore());
            statment.setString(10, student.getStatus());
            
//          executeUpdate => Số lượng dòng được cập nhật hoặc thêm mới hoặc xoá trong db
            int changeRowsNum = statment.executeUpdate();
            
            return changeRowsNum > 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
//  Cập nhật thông tin của SV
//  Tìm sinh viên theo MSSV và cho phép cập nhật họ tên, địa chỉ, số điện thoại và các cột điểm.
//+ "(full_name, address, phone, lab_score, quiz_score, assignment_score, final_exam_score, average_score, status) VALUES"
    public boolean updateStudent(Student student){
        Connection conn = DatabaseConnect.connection();
        try{
            String update = "UPDATE students SET full_name=?, address=?,"
                    + " phone=?, lab_score=?, quiz_score=?, assignment_score=?,"
                    + " final_exam_score=?, average_score=?, status=? WHERE id=?";
            
            PreparedStatement statment = conn.prepareStatement(update);
            
            statment.setString(1, student.getFullName());
            statment.setString(2, student.getAddress());
            statment.setString(3, student.getPhone());
            statment.setFloat(4, student.getLabScore());
            statment.setFloat(5, student.getQuizScore());
            statment.setFloat(6, student.getAssignmentScore());
            statment.setFloat(7, student.getFinalExamScore());
            statment.setFloat(8, student.getAverageScore());
            statment.setString(9, student.getStatus());
            statment.setInt(10, student.getId());
            
            int changeRowsNum = statment.executeUpdate();
            
            return changeRowsNum > 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
//  Xoá sinh viên
    public boolean deleteStudent(int id){
        Connection conn = DatabaseConnect.connection();
        try{
            String delete = "DELETE FROM students WHERE id=?";
            
            PreparedStatement statment = conn.prepareStatement(delete);
            statment.setInt(1, id);
            
            int changeRowsNum = statment.executeUpdate();
            
            return changeRowsNum > 0;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
//  Tìm kiếm gần đúng theo họ và tên
//  Nguyễn Văn An
//  Nguyễn || Văn || An || Nguyễn Văn || Văn An || Nguyễn An abc xyz
//  LIKE %Nguyễn% OR LIKE %An% OR LIKE %abc% ...
    public List<Student> getStudentByName(String name){
        List<Student> students = new ArrayList();
        Connection conn = DatabaseConnect.connection();
        try{
//         split(" ") == Nguyễn An abc xyz => Mảng ["Nguyễn", "An", "abc", "xyz"]
//         trim() => Loại bỏ khoảng trắng dư ở 2 đầu chuỗi 
//         split(" ") => Cắt chuỗi ra thành mảng vị trí cắt tại ký tự " "
//         join => Chuyển mảng về thành chuỗi 
           String[] names = name.trim().split(" ");
//         names => sẽ có ít nhất 1 item nên names[0] chắc chắn không lỗi
           String query = "full_name LIKE '%" + names[0] + "%'";
           for(int index = 1; index < names.length; index++){
               query += " OR full_name LIKE '%" + names[index] + "%'";
           }
           
//         full_name LIKE '%Nguyễn%' OR full_name LIKE '%An%' ....
            
           String select = "SELECT * FROM student WHERE " + query;
           
           PreparedStatement statment = conn.prepareStatement(select);
           ResultSet rs = statment.executeQuery();

           while(rs.next()){
    //              Lấy dữ liệu từ db ra
               int id = rs.getInt("id");
               String studentCode = rs.getString("student_code");
               String fullName = rs.getString("full_name");
               String address = rs.getString("address");
               String phone = rs.getString("phone");
               float labScore = rs.getFloat("lab_score");
               float quizScore = rs.getFloat("quiz_score");
               float assignmentScore = rs.getFloat("assignment_score");
               float finalExamScore = rs.getFloat("final_exam_score");
               float averageScore = rs.getFloat("average_score");
               String status = rs.getString("status");

    //              Lưu dữ liệu đã lấy được vào đối tượng 
               Student student = new Student();
               student.setId(id);
               student.setStudentCode(studentCode);
               student.setFullName(fullName);
               student.setAddress(address);
               student.setPhone(phone);
               student.setLabScore(labScore);
               student.setQuizScore(quizScore);
               student.setAssignmentScore(assignmentScore);
               student.setFinalExamScore(finalExamScore);
               student.setAverageScore(averageScore);
               student.setStatus(status);

    //              Thêm đối tượng vừa được lưu thông tin vào danh sách
               students.add(student);
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    
//  Lọc theo trạng thái
//  Dữ liệu đầu vào true || false
//  Nếu là true thì lấy danh sách SV trạng thái là đạt
//  Ngược lại lấy danh sách SV có trạng thái là không đạt
    public List<Student> getStudentByStatus(boolean status){
        List<Student> students = new ArrayList<>();
        Connection conn = DatabaseConnect.connection();
        try{
            String sql = "SELECT * from students WHERE status=?";
            PreparedStatement statment = conn.prepareStatement(sql);
            if(status){
                statment.setString(1, "PASS");
            }else{
                statment.setString(1, "FAIL");
            }
            ResultSet rs = statment.executeQuery();
            
            while(rs.next()){
//              Lấy dữ liệu từ db ra
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float labScore = rs.getFloat("lab_score");
                float quizScore = rs.getFloat("quiz_score");
                float assignmentScore = rs.getFloat("assignment_score");
                float finalExamScore = rs.getFloat("final_exam_score");
                float averageScore = rs.getFloat("average_score");
                String statusSQL = rs.getString("status");
                
//              Lưu dữ liệu đã lấy được vào đối tượng 
                Student student = new Student();
                student.setId(id);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                student.setPhone(phone);
                student.setLabScore(labScore);
                student.setQuizScore(quizScore);
                student.setAssignmentScore(assignmentScore);
                student.setFinalExamScore(finalExamScore);
                student.setAverageScore(averageScore);
                student.setStatus(statusSQL);   
//              Thêm đối tượng vừa được lưu thông tin vào danh sách
                students.add(student);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return students;
    }
    
//  asc || desc
    public List<Student> getListSortByAvgScore(boolean asc){
        List<Student> students = new ArrayList<>();
        Connection conn = DatabaseConnect.connection();
        try{
            String sql = "SELECT * FROM ORDER BY average_score ";
            if(asc){
                sql += "ASC";
            }else{
                sql += "DESC";
            }
            PreparedStatement statment = conn.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            
            while(rs.next()){
//              Lấy dữ liệu từ db ra
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float labScore = rs.getFloat("lab_score");
                float quizScore = rs.getFloat("quiz_score");
                float assignmentScore = rs.getFloat("assignment_score");
                float finalExamScore = rs.getFloat("final_exam_score");
                float averageScore = rs.getFloat("average_score");
                String statusSQL = rs.getString("status");
                
//              Lưu dữ liệu đã lấy được vào đối tượng 
                Student student = new Student();
                student.setId(id);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                student.setPhone(phone);
                student.setLabScore(labScore);
                student.setQuizScore(quizScore);
                student.setAssignmentScore(assignmentScore);
                student.setFinalExamScore(finalExamScore);
                student.setAverageScore(averageScore);
                student.setStatus(statusSQL);   
//              Thêm đối tượng vừa được lưu thông tin vào danh sách
                students.add(student);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return students;
    }
    
    //  asc || desc
    public List<Student> getListSortByName(boolean asc){
        List<Student> students = new ArrayList<>();
        Connection conn = DatabaseConnect.connection();
        try{
            String sql = "SELECT * FROM ORDER BY full_name ";
            if(asc){
                sql += "ASC";
            }else{
                sql += "DESC";
            }
            PreparedStatement statment = conn.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            
            while(rs.next()){
//              Lấy dữ liệu từ db ra
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                float labScore = rs.getFloat("lab_score");
                float quizScore = rs.getFloat("quiz_score");
                float assignmentScore = rs.getFloat("assignment_score");
                float finalExamScore = rs.getFloat("final_exam_score");
                float averageScore = rs.getFloat("average_score");
                String statusSQL = rs.getString("status");
                
//              Lưu dữ liệu đã lấy được vào đối tượng 
                Student student = new Student();
                student.setId(id);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);
                student.setPhone(phone);
                student.setLabScore(labScore);
                student.setQuizScore(quizScore);
                student.setAssignmentScore(assignmentScore);
                student.setFinalExamScore(finalExamScore);
                student.setAverageScore(averageScore);
                student.setStatus(statusSQL);   
//              Thêm đối tượng vừa được lưu thông tin vào danh sách
                students.add(student);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return students;
    }
    
//  Thống kê
//  Tổng số sinh viên, số lượng Đạt, số lượng Không đạt, 
//  điểm trung bình của lớp, sinh viên có điểm cao nhất và thấp nhất.
//  Muốn thống kê được tất cả thông tin này
//  - Cách 1: Viết từng câu lệnh select để lấy từng giá trị ra
//      + Ưu điểm: Dữ liệu luôn chính xác
//      + Nhược điểm: Cần truy xuất db nhiều lần => Tốn tài nguyên và thời gian
//  - Cách 2: Viết 1 câu lệnh sql trả về hết danh sách
//          => Dùng code ở java lọc các giá trị cần thiết
//      + Ưu điểm: Nhanh, không cần xử lý câu lệnh sql phức tạp
//      + Nhược điểm: Tốn tài nguyên => Do cần phải lấy hết danh sách SV
//          => Cần xử toàn bộ danh sách
    
    public Statistical statistical(){
        Statistical statisticalDB = new Statistical();
        try{
//          Trả về danh sách sinh viên hiện có
            List<Student> students = this.getList();
            if(students.isEmpty()){
                return statisticalDB;
            }
//          Tổng số sinh viên
            statisticalDB.setTotalStudent(students.size());
            int totalPass = 0;
            int totalFail = 0;
            float totalScore = 0;
            Student maxScore = students.get(0);
            Student minScore = students.get(0);
            for(Student student : students){
                if(student.getStatus().equals("PASS")){
                    totalPass++;
                }else{
                    totalFail++;
                }
                totalScore += student.getAverageScore();
                if(student.getAverageScore() > maxScore.getAverageScore()){
                    maxScore = student;
                }
                if(student.getAverageScore() < minScore.getAverageScore()){
                    minScore = student;
                }
            }
            float averageScoreClass = totalScore / students.size();
            statisticalDB.setTotalPass(totalPass);
            statisticalDB.setTotalFail(totalFail);
            statisticalDB.setAverageClass(averageScoreClass);
            statisticalDB.setStudentMaxScore(maxScore);
            statisticalDB.setStudentMinScore(minScore);
        }catch(Exception e){
            e.printStackTrace();
        }
        return statisticalDB;
    }
}
