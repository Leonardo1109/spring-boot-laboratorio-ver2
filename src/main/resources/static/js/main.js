// Orquestador
import { EquiposPage } from "./pages/equipos.js"
import { AsistenciaActivaPage } from "./pages/asistencia.js"

document.body.addEventListener("htmx:afterSwap", (evt) => {
    if (evt.target.id !== "main-content") return;

    if (evt.target.querySelector('[data-page="equipos"]')) {
        EquiposPage.init();
    }

    if (evt.target.querySelector('[data-page="asistencia-activa"]')) {
        AsistenciaActivaPage.init();
    }
});