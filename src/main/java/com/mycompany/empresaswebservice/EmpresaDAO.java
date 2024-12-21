/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresaswebservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
    private static final String USER = "seu_usuario";
    private static final String PASS = "sua_senha";

    public void deletarEmpresa(int id) throws SQLException {
        String sql = "DELETE FROM Empresas WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void atualizarEmpresa(Empresa empresa) throws SQLException {
        String sql = "UPDATE Empresas SET nome = ?, endereco = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getEndereco());
            stmt.setInt(3, empresa.getId());
            stmt.executeUpdate();
        }
    }

    public void criarEmpresa(Empresa empresa) throws SQLException {
        String sql = "INSERT INTO Empresas (nome, endereco) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getEndereco());
            stmt.executeUpdate();
        }
    }

    public List<Empresa> listarEmpresas() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Empresas")) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEndereco(rs.getString("endereco"));
                empresas.add(empresa);
            }

        }
        return empresas;
    }
}
