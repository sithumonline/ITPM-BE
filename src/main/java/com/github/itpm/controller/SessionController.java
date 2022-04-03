package com.github.itpm.controller;

import com.github.itpm.model.Session;
import com.github.itpm.service.SessionService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8080
 *http://localhost:8080/api/v1/session/*
 */
@Path("/api/v1/session")
public class SessionController {

    private Session session;
    private final SessionService sessionService = new SessionService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSession(HashMap<String, ?> sessionData) {
        String name = (String) sessionData.get("name");
        String date = (String) sessionData.get("date");
        String time = (String) sessionData.get("time");
        String link = (String) sessionData.get("link");
        session = new Session(name, date, time, link);

        return sessionService.addSession(session);
    }

    @PUT
    @Path("/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSession(HashMap<String, ?> sessionData, @PathParam("sessionId") Integer sessionId) {
        String name = (String) sessionData.get("name");
        String date = (String) sessionData.get("date");
        String time = (String) sessionData.get("time");
        String link = (String) sessionData.get("link");
        session = new Session(name, date, time, link);
        session.setId(sessionId);

        return sessionService.updateSession(session);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessions() {
        return sessionService.getSessions();
    }

    @GET
    @Path("/{sessionId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionById(@PathParam("sessionId") Integer sessionId) {
        return sessionService.getSessionById(sessionId);
    }

    @DELETE
    @Path("/{sessionId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("sessionId") Integer sessionId) {
        return sessionService.deleteSession(sessionId);
    }
}
