package com.github.itpm.controller;

import com.github.itpm.model.Teacher;
import com.github.itpm.service.TeacherService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8080
 *http://localhost:8080/api/v1/teacher/*
 */
@Path("/api/v1/teacher")
public class TeacherController {

    private Teacher teacher;
    private final TeacherService teacherService = new TeacherService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(HashMap<String, ?> teacherData) {
        String name = (String) teacherData.get("name");
        String dob = (String) teacherData.get("dob");
        String address = (String) teacherData.get("address");
        String phone = (String) teacherData.get("phone");
        teacher = new Teacher(name, dob, address, phone);

        return teacherService.addTeacher(teacher);
    }

    @PUT
    @Path("/{teacherId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(HashMap<String, ?> teacherData, @PathParam("teacherId") Integer teacherId) {
        String name = (String) teacherData.get("name");
        String dob = (String) teacherData.get("dob");
        String address = (String) teacherData.get("address");
        String phone = (String) teacherData.get("phone");
        teacher = new Teacher(name, dob, address, phone);
        teacher.setId(teacherId);

        return teacherService.updateTeacher(teacher);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachers() {
        return teacherService.getTeachers();
    }

    @GET
    @Path("/{teacherId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherById(@PathParam("teacherId") Integer teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @DELETE
    @Path("/{teacherId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("teacherId") Integer teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }

    @GET
    @Path("/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchTeacher(@QueryParam("name") String name) {
        return teacherService.searchTeacher(name);
    }
}
