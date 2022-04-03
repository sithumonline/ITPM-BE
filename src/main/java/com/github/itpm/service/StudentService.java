package com.github.itpm.service;

import com.github.itpm.model.Student;
import com.github.itpm.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
	private DBConnection connection = new DBConnection();

	public Response addStudent(Student student) {
		int insertedId = -99;
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "INSERT INTO student(name,dob,address,phone) VALUES (?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, student.getName());
			preparedStmt.setString(2, student.getDob());
			preparedStmt.setString(3, student.getAddress());
			preparedStmt.setString(4, student.getPhone());

			preparedStmt.execute();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()){
				insertedId  = Integer.parseInt(rs.getString(1));
			}
			con.close();

			student.setId(insertedId);
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(student)
				.build();
	}

	public Response getStudents() {
		List<Student> categories = new ArrayList<Student> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from student";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				Student student = new Student(name, dob, address, phone);
				student.setId(id);
				categories.add(student);

			}
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(categories)
				.build();
	}

	public Response getStudentById(Integer studentid) {
		Student student = null;

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from student where id = " + studentid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				student = new Student(name, dob, address, phone);
				student.setId(id);
			}
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(student)
				.build();
	}

	public Response deleteStudent(Integer studentid) {
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "DELETE from student WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, studentid);

			preparedStmt.execute();
			con.close();

		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity("Product Student deleted successfully")
				.build();
	}

	public Response updateStudent(Student student) {
		try
		{
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "UPDATE student SET name=?,dob=?,address=?,phone=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, student.getName());
			preparedStmt.setString(2, student.getDob());
			preparedStmt.setString(3, student.getAddress());
			preparedStmt.setString(4, student.getPhone());
			preparedStmt.setInt(5, student.getId());

			preparedStmt.execute();
			con.close();
		}
		catch (Exception e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error while updating the item")
					.build();
		}

		return Response
				.status(Response.Status.CREATED)
				.entity(student)
				.build();
	}

}
