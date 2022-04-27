package com.github.itpm.service;

import com.github.itpm.model.Teacher;
import com.github.itpm.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherService {
	private DBConnection connection = new DBConnection();

	public Response addTeacher(Teacher teacher) {
		int insertedId = -99;
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "INSERT INTO teacher(name,dob,address,phone) VALUES (?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStmt.setString(1, teacher.getName());
			preparedStmt.setString(2, teacher.getDob());
			preparedStmt.setString(3, teacher.getAddress());
			preparedStmt.setString(4, teacher.getPhone());

			preparedStmt.execute();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()){
				insertedId  = Integer.parseInt(rs.getString(1));
			}
			con.close();

			teacher.setId(insertedId);
		} catch (Exception e) {
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e)
					.build();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(teacher)
				.build();
	}

	public Response getTeachers() {
		List<Teacher> categories = new ArrayList<Teacher> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from teacher";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				Teacher teacher = new Teacher(name, dob, address, phone);
				teacher.setId(id);
				categories.add(teacher);

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

	public Response getTeacherById(Integer teacherid) {
		Teacher teacher = null;

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from teacher where id = " + teacherid;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				teacher = new Teacher(name, dob, address, phone);
				teacher.setId(id);
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
				.entity(teacher)
				.build();
	}

	public Response deleteTeacher(Integer teacherid) {
		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "DELETE from teacher WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, teacherid);

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
				.entity("Product Teacher deleted successfully")
				.build();
	}

	public Response updateTeacher(Teacher teacher) {
		try
		{
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "UPDATE teacher SET name=?,dob=?,address=?,phone=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, teacher.getName());
			preparedStmt.setString(2, teacher.getDob());
			preparedStmt.setString(3, teacher.getAddress());
			preparedStmt.setString(4, teacher.getPhone());
			preparedStmt.setInt(5, teacher.getId());

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
				.entity(teacher)
				.build();
	}

    public Response searchTeacher(String name) {
		List<Teacher> teachers = new ArrayList<Teacher> ();

		try {
			Connection con = connection.getConnection();
			if (con == null) return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("DataBase connectivity Error")
					.build();

			String query = "select * from teacher where name like '%"+name+"%'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				String dob = rs.getString("dob");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				Teacher teacher = new Teacher(name1, dob, address, phone);
				teacher.setId(id);
				teachers.add(teacher);

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
				.entity(teachers)
				.build();
	}
}
