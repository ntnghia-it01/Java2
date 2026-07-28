/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fpoly.java2;

import com.fpoly.java2.config.DatabaseConnect;
import com.fpoly.java2.dao.StudentDAO;
import com.fpoly.java2.models.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author trongnghia
 */
public class Main {

    public static void main(String[] args) {
        menu();
    }
    
//  Xây dựng 1 hàm để hiển thị menu
    public static void menu(){
        while(true){
            System.out.println("=========Chọn các số tương ứng với chức năng=========");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Lọc theo trạng thái");
            System.out.println("7. Sắp xếp danh sách");
            System.out.println("8. Thống kê lớp học");
            System.out.println("9. Thoát chương trình");
            int chooseMenu = 0;
            System.out.print("Vui lòng chọn chức năng: ");
            Scanner scanner = new Scanner(System.in);
            chooseMenu = scanner.nextInt();
            scanner.nextLine();
            switch(chooseMenu){
                case 1:
                    System.out.println("1. Hiển thị danh sách sinh viên");
                    showStudentList();
                    break;
                case 2:
                    System.out.println("2. Thêm sinh viên");
                    addStudent();
                    break;
                case 3:
                    System.out.println("3. Cập nhật sinh viên");
                    break;
                case 4:
                    System.out.println("4. Xóa sinh viên");
                    break;
                case 5:
                    System.out.println("5. Tìm kiếm sinh viên");
                    break;
                case 6:
                    System.out.println("6. Lọc theo trạng thái");
                    break;
                case 7:
                    System.out.println("7. Sắp xếp danh sách");
                    break;
                case 8:
                    System.out.println("8. Thống kê lớp học");
                    break;
                case 9:
                    System.out.println("9. Thoát chương trình");
                    return;
                default:
                    System.out.println("Chức năng không tồn tại. Vui lòng chọn lại!");
                    break;   
            }
        }
    }
    
//  Hiển thị danh sách sv từ db
    private static void showStudentList(){
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getList();
        System.out.printf("Danh sách SV hiện có %d bạn\n", students.size());
        for(Student student : students){
            System.out.printf("-----------------------------\n");
            showStudentDetail(student);
        }
    }
    
//  Hiển thị thông tin chi tiết của 1 SV 
//  Tái sử dụng lại vì sẽ có nhiều chức năng cần hiển thị chi tiết thông tin
    private static void showStudentDetail(Student student){
        System.out.printf("MSSV: %s\n", student.getStudentCode());
        System.out.printf("Họ và tên: %s\n", student.getFullName());
        System.out.printf("Địa chỉ: %s\n", student.getAddress());
        System.out.printf("Số điện thoại: %s\n", student.getPhone());
        System.out.printf("Điểm bài Lab: %.2f\n", student.getLabScore());
        System.out.printf("Điểm bài Quiz: %.2f\n", student.getQuizScore());
        System.out.printf("Điểm bài Assignment: %.2f\n", student.getAssignmentScore());
        System.out.printf("Điểm bài cuối môn: %.2f\n", student.getFinalExamScore());
        System.out.printf("Điểm trung bình môn: %.2f\n", student.getAverageScore());
//      Trạng thái sẽ lưu DB là PASS hoặc FAIL
        System.out.printf("Trạng thái môn: %s\n", 
                student.getStatus().equals("PASS") ? "Đạt" : "Không đạt");
    }
    
//  Thêm SV vào DB
//  Cho người dùng nhập trước MSSV => Kiểm tra MSSV có trùng không?
//  Nếu trùng cho nhập lại
//  Sau khi nhập đúng MSSV => Nhập thông tin liên quan
//  Không nhập điểm trung bình và trạng thái => Sẽ tự tính toán bằng code
//  Điểm TB thì tính theo trọng số 15, 5, 30, 50
//  Trạng thái thì dựa vào điểm TB => PASS (điểm TB >= 5) || FAIL (điểm TB < 5)
    private static void addStudent(){
        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
//      Đối tượng này dùng để lưu thông tin từ người dùng nhập vào
        Student studentInput = new Student();
        
        String studentCode = enterStudentCode();
        studentInput.setStudentCode(studentCode);
        
        String name = enterText("Nhập họ tên: ", "Họ tên không được để trống. Nhập lại!");
        studentInput.setFullName(name);
        
        String address = enterText("Nhập địa chỉ: ", "Địa chỉ không được để trống. Nhập lại!");
        studentInput.setAddress(address);
        
        String phone = enterPhoneNumber();
        studentInput.setPhone(phone);
        
        float labScore = enterScore("Nhập điểm bài Lab: ");
        studentInput.setLabScore(labScore);
        
        float quizScore = enterScore("Nhập đểm bài Quiz: ");
        studentInput.setQuizScore(quizScore);
        
        float assignmentScore = enterScore("Nhập điểm bài Assignment: ");
        studentInput.setAssignmentScore(assignmentScore);
        
        float finalScore = enterScore("Nhập điểm cuối môn: ");
        studentInput.setFinalExamScore(finalScore);
        
        //  Điểm TB thì tính theo trọng số 15, 5, 30, 50
        double averageScore = studentInput.getLabScore() * 0.15 
                + studentInput.getQuizScore() * 0.05 
                + studentInput.getAssignmentScore() * 0.3
                + studentInput.getFinalExamScore() * 0.5;
        
        studentInput.setAverageScore((float) averageScore);
        studentInput.setStatus(averageScore >= 5 ? "PASS" : "FAIL");
        
        boolean insert = studentDAO.insertStudent(studentInput);
        
        if(insert){
            System.out.printf("Thêm SV với MSSV là %s thành công!\n", studentInput.getStudentCode());
        }else{
            System.out.printf("Thêm SV với MSSV là %s thất bại!\n", studentInput.getStudentCode());
        }   
    }
    
//  Hàm này sẽ cho người dùng nhập 1 đoạn nội dung
//  Kiểm tra nếu người dùng bỏ trống hoặc nhập nhiều dấu space
//  Yêu cầu user nhập lại
//  Đến khi thoả yêu cầu => return về nội dung user đã nhập
//  title: Nội dung thông trước khi người dùng nhập 
//  error: Nội dung khi lỗi xảy ra
    private static String enterText(String title, String error){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.printf(title);
            String content = scanner.nextLine();
            if(!content.isBlank()){
                return content;
            }
            System.out.println(error);
        }
    }
    
    private static String enterStudentCode(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Nhập MSSV: ");
            String studentCode = scanner.nextLine();
            
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getByStudentCode(studentCode);
//          MSSV không rỗng và không tồn tại MSSV trong db
            if(!studentCode.isBlank() && student == null){
                return studentCode;
            }
            System.out.printf("MSSV đã tồn tại vui lòng nhập lại!\n");
        }
    }
    
    private static String enterPhoneNumber(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Nhập số điện thoại: "); // Số điện thoại đúng định dạng 
            String phone = scanner.nextLine();
//          Bắt đầu bằng số 0 và có 10 số => regex
            if(phone.matches("^0\\d{9}$")){
                return phone;
            }
            System.out.printf("Số điện thoại không đúng định dạng nhập lại!\n");
        }
    }
    
    private static float enterScore(String title){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.printf(title);
            try{
                float score = scanner.nextFloat();
//              Loại bỏ ký tự enter sau khi nhập số 
                scanner.nextLine();
                if(score >= 0 && score <= 10){
                    return score;
                }
            }catch(Exception e){}
            System.out.printf("Điểm phải là số thực nằm trong khoản từ 0 -> 10\n");
        }
    }
}
