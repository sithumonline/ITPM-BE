package com.github.itpm.controller;

import com.github.itpm.model.Student;
import com.github.itpm.service.StudentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8080
 *http://localhost:8080/api/v1/student/*
 */
@Path("/api/v1/student")
public class StudentController {

    private Student student;
    private final StudentService studentService = new StudentService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(HashMap<String, ?> studentData) {
        String name = (String) studentData.get("name");
        String dob = (String) studentData.get("dob");
        String address = (String) studentData.get("address");
        String phone = (String) studentData.get("phone");
        student = new Student(name, dob, address, phone);

        return studentService.addStudent(student);
    }

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(HashMap<String, ?> studentData, @PathParam("studentId") Integer studentId) {
        String name = (String) studentData.get("name");
        String dob = (String) studentData.get("dob");
        String address = (String) studentData.get("address");
        String phone = (String) studentData.get("phone");
        student = new Student(name, dob, address, phone);
        student.setId(studentId);

        return studentService.updateStudent(student);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        return studentService.getStudents();
    }

    @GET
    @Path("/{studentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("studentId") Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @DELETE
    @Path("/{studentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("studentId") Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GET
    @Path("/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchStudent(@QueryParam("name") String name) {
        return studentService.searchStudent(name);
    }
}
