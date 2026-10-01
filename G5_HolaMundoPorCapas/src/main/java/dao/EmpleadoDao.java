/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Map;
import model.Empleado;

/**
 *
 * @author anazk
 */
public interface EmpleadoDao {

   public Map<String, Empleado> obtenerTodos();
}
