package com.mgcss.api;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mgcss.domain.Cliente;
import com.mgcss.domain.EstadoCliente;
import com.mgcss.domain.EstadoSolicitud;
import com.mgcss.domain.Solicitud;
import com.mgcss.service.ServicioSolicitud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SolicitudController.class)
@Import(SolicitudMapper.class)
class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServicioSolicitud servicioSolicitud;

    @Test
    @DisplayName("POST /api/solicitudes crea una solicitud correctamente")
    void crearSolicitudDevuelveSolicitudCreada() throws Exception {
        Solicitud solicitud = crearSolicitudEjemplo(
                1L,
                "Incidencia de prueba",
                EstadoSolicitud.ABIERTA
        );

        given(servicioSolicitud.crearSolicitud(eq("Incidencia de prueba")))
                .willReturn(solicitud);

        mockMvc.perform(post("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "descripcion": "Incidencia de prueba"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Incidencia de prueba"))
                .andExpect(jsonPath("$.estado").value("ABIERTA"));
    }

    @Test
    @DisplayName("POST /api/solicitudes devuelve 400 si la descripción está vacía")
    void crearSolicitudSinDescripcionDevuelveBadRequest() throws Exception {
        mockMvc.perform(post("/api/solicitudes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "descripcion": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    private Solicitud crearSolicitudEjemplo(Long id, String descripcion, EstadoSolicitud estado) {
        Cliente cliente = new Cliente(
                1L,
                "Cliente API",
                "api@test.com",
                EstadoCliente.STANDARD
        );

        return new Solicitud(id, cliente, descripcion, estado);
    }
}