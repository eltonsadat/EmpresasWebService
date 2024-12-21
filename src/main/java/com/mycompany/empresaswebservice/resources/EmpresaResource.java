/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresaswebservice.resources;

import com.mycompany.empresaswebservice.Empresa;
import com.mycompany.empresaswebservice.EmpresaDAO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;

import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

@Path("/empresas")
public class EmpresaResource {

    private final EmpresaDAO empresaDAO = new EmpresaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> listarEmpresas() throws SQLException {
        return empresaDAO.listarEmpresas();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarEmpresa(@PathParam("id") int id) {
        try {
            empresaDAO.deletarEmpresa(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar empresa").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEmpresa(@PathParam("id") int id, Empresa empresa) {
        try {
            empresa.setId(id);
            empresaDAO.atualizarEmpresa(empresa);
            return Response.ok().entity(empresa).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar empresa").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarEmpresa(Empresa empresa) {
        try {
            empresaDAO.criarEmpresa(empresa);
            return Response.status(Response.Status.CREATED).entity(empresa).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar empresa").build();
        }
    }
}
