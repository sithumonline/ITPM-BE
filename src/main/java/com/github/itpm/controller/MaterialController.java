package com.github.itpm.controller;

import com.github.itpm.model.Material;
import com.github.itpm.service.MaterialService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8080
 *http://localhost:8080/api/v1/material/*
 */
@Path("/api/v1/material")
public class MaterialController {

    private Material material;
    private final MaterialService materialService = new MaterialService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMaterial(HashMap<String, ?> materialData) {
        String title = (String) materialData.get("title");
        String author = (String) materialData.get("author");
        String subject = (String) materialData.get("subject");
        String grade = (String) materialData.get("grade");
        material = new Material(title, author, subject, grade);

        return materialService.addMaterial(material);
    }

    @PUT
    @Path("/{materialId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMaterial(HashMap<String, ?> materialData, @PathParam("materialId") Integer materialId) {
        String title = (String) materialData.get("title");
        String author = (String) materialData.get("author");
        String subject = (String) materialData.get("subject");
        String grade = (String) materialData.get("grade");
        material = new Material(title, author, subject, grade);
        material.setId(materialId);

        return materialService.updateMaterial(material);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaterials() {
        return materialService.getMaterials();
    }

    @GET
    @Path("/{materialId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaterialById(@PathParam("materialId") Integer materialId) {
        return materialService.getMaterialById(materialId);
    }

    @DELETE
    @Path("/{materialId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("materialId") Integer materialId) {
        return materialService.deleteMaterial(materialId);
    }
}
