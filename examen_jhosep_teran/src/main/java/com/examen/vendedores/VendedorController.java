package com.examen.vendedores;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {
    private AdminVentas admin = new AdminVentas();

    @PostMapping("/agregar")
    public void agregarVendedor(@RequestBody Vendedor v) {
        Vendedor nuevo;
        if (v.getTipo().equals("V")) {
            nuevo = new Vendedor(v.getCedula(), "V");
        } else if (v.getTipo().equals("C")) {
            nuevo = new VendedorComision(v.getCedula());
        } else if (v.getTipo().equals("M")) {
            nuevo = new VendedorMixto(v.getCedula());
        } else {
            return;
        }
        nuevo.setNumeroVentas(v.getNumeroVentas());
        nuevo.setSueldoFijo(v.getSueldoFijo());
        nuevo.setComisionPorVenta(v.getComisionPorVenta());
        admin.agregar(nuevo);
    }

    @GetMapping("/calcular/{cedula}")
    public Double calcularSueldoVendedor(@PathVariable String cedula) {
        return admin.calcularSueldo(cedula);
    }
    
    @GetMapping("/error")
    public void generarError() {
        Vendedor v = null;
        v.getCedula();
    }
}