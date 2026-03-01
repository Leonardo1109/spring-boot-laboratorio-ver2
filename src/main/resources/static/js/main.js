// Orquestador
import { EquiposPage } from "./pages/equipos.js"
import { AsistenciaActivaPage } from "./pages/asistencia.js"
import { ProyectoPage } from "./pages/proyecto.js";

document.body.addEventListener("htmx:afterSwap", (evt) => {
    if (evt.target.id !== "main-content") return;

    if (evt.target.querySelector('[data-page="equipos"]')) {
        EquiposPage.init();
    }

    if (evt.target.querySelector('[data-page="asistencia-activa"]')) {
        AsistenciaActivaPage.init();
    }

    if (evt.target.querySelector('[data-page="proyecto-crear"]')) {
        ProyectoPage.init("crear");
    }

    if (evt.target.querySelector('[data-page="proyecto-editar"]')) {
        ProyectoPage.init("editar");
    }
});