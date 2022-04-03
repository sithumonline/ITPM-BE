package com.github.itpm.service;

import com.github.itpm.model.Session;
import com.github.itpm.repository.DBConnection;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private DBConnection connection = new DBConnection();

    public Response addSession(Session session) {
        int insertedId = -99;
        try {
            Connection con = connection.getConnection();
            if (con == null) return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("DataBase connectivity Error")
                    .build();

            String query = "INSERT INTO session(name,date,time,link) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStmt.setString(1, session.getName());
            preparedStmt.setString(2, session.getDate());
            preparedStmt.setString(3, session.getTime());
            preparedStmt.setString(4, session.getLink());

            preparedStmt.execute();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                insertedId = Integer.parseInt(rs.getString(1));
            }
            con.close();

            session.setId(insertedId);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e)
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(session)
                .build();
    }

    public Response getSessions() {
        List<Session> categories = new ArrayList<Session>();

        try {
            Connection con = connection.getConnection();
            if (con == null) return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("DataBase connectivity Error")
                    .build();

            String query = "select * from session";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String link = rs.getString("link");
                Session session = new Session(name, date, time, link);
                session.setId(id);
                categories.add(session);

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

    public Response getSessionById(Integer sessionid) {
        Session session = null;

        try {
            Connection con = connection.getConnection();
            if (con == null) return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("DataBase connectivity Error")
                    .build();

            String query = "select * from session where id = " + sessionid;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String link = rs.getString("link");
                session = new Session(name, date, time, link);
                session.setId(id);
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
                .entity(session)
                .build();
    }

    public Response deleteSession(Integer sessionid) {
        try {
            Connection con = connection.getConnection();
            if (con == null) return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("DataBase connectivity Error")
                    .build();

            String query = "DELETE from session WHERE id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, sessionid);

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
                .entity("Product Session deleted successfully")
                .build();
    }

    public Response updateSession(Session session) {
        try {
            Connection con = connection.getConnection();
            if (con == null) return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("DataBase connectivity Error")
                    .build();

            String query = "UPDATE session SET name=?,date=?,time=?,link=? WHERE id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, session.getName());
            preparedStmt.setString(2, session.getDate());
            preparedStmt.setString(3, session.getTime());
            preparedStmt.setString(4, session.getLink());
            preparedStmt.setInt(5, session.getId());

            preparedStmt.execute();
            con.close();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while updating the item")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(session)
                .build();
    }

}
