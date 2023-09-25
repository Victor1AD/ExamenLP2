package org.cibertec.edu.pe.controller;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.interfaces.IEmpleado;
import org.cibertec.edu.pe.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private IEmpleado emp;

    @GetMapping("/lista")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = emp.findAll();
        model.addAttribute("empleados", empleados);
        return "lista-empleados";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "formulario-registro";
    }

    @PostMapping("/registro")
    public String registrarEmpleado(@ModelAttribute Empleado empleado) {
        emp.save(empleado);
        return "redirect:/empleados/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Optional<Empleado> empleado = emp.findById(id);
        empleado.ifPresent(e -> model.addAttribute("empleado", e));
        return "formulario-edicion";
    }

    @PostMapping("/editar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado) {
        emp.save(empleado);
        return "redirect:/empleados/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable String id) {
        emp.deleteById(id);
        return "redirect:/empleados/lista";
    }
}
