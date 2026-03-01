export class ProyectoPage {

    static init(tipo) {

        this.tipo = tipo;
        this.root = document.querySelector(`[data-page="proyecto-${tipo}"]`);
        if (!this.root) return;
        this.resetState(); // reiniciar la variable
        const page = this.root.dataset.page;

        if (page === "proyecto-crear") {
            console.log("Create");
            this.initCrear();
        }

        if (page === "proyecto-editar") {
            console.log("Edit");
            this.initEditar();
        }
    }

    // ================================================================================= //
    //                               Estado del proyecto                                 //
    // ================================================================================= //

    static resetState() {
        this.state = {
            nombre: "",
            descripcion: "",
            objetivos: "",
            clave: "",
            actividades: [],
            responsables: new Map(),
            carreras: new Set()
        }
    }

    // ================================================================================= //
    //                                      INIT                                         //
    // ================================================================================= //

    static initCrear() {
        this.cargarCarreras();
        this.bindActividades();
        this.initResponsables();
        this.bindSubmit(); // Mandar
    }

    static async initEditar() {
        this.bindSubmit();
        this.bindActividades();
        this.initResponsables();
    
        await this.cargarCarreras();
        
        const id = this.root.dataset.id;
        await this.cargarProyecto(id);
        
    }

    // ================================================================================= //
    //                                    HYDRATION                                      //
    // ================================================================================= //

    static async cargarProyecto(id) {
        try {
            const response = await fetch(`/api/proyectos/${id}`);
            if (!response.ok) {
                throw new Error("Error cargando proyecto");
            }
    
            const data = await response.json();
            this.hidratarFormulario(data);
    
        } catch (error) {
            console.error(error);
            alert("Error cargando proyecto");
        }
    }

    static hidratarFormulario(data) {

        // =========================
        // 1. Campos simples
        // =========================
        this.root.querySelector("[name='nombre']").value = data.nombre ?? "";
        this.root.querySelector("[name='descripcion']").value = data.descripcion ?? "";
        this.root.querySelector("[name='objetivos']").value = data.objetivos ?? "";
        this.root.querySelector("[name='clave']").value = data.clave ?? "";
    
        // =========================
        // 2. Carreras
        // =========================
        data.carreras?.forEach(c => {
            this.state.carreras.add(c.id);
        });
    
        this.syncCarrerasUI();
    
        // =========================
        // 3. Responsables
        // =========================
        data.visitas?.forEach(v => {
            this.state.responsables.set(v.id, v);
        });
    
        this.renderSeleccionados();
    
        // =========================
        // 4. Actividades
        // =========================
        const container = this.root.querySelector("#actividades-container");
        container.innerHTML = "";
    
        data.actividades?.forEach(a => {
            this.agregarActividadUI(a);
        });
    }

    // ================================================================================= //
    //                                     CARRERAS                                      //
    // ================================================================================= //

    // Peticion al backend y contruccion de los elementos
    static async cargarCarreras() {
        const response = await fetch("/api/carreras");
        const carreras = await response.json();
    
        const container = this.root.querySelector("#carreras-container");
        if (!container) return;
        container.innerHTML = "";
    
        carreras.forEach(c => {
            const col = document.createElement("div");
            col.className = "col-12 col-md-6 col-lg-4";
    
            col.innerHTML = `
                <div class="card border shadow-sm h-100 carrera-card"
                    data-id="${c.id}"
                    style="cursor:pointer;">
                    <div class="card-body text-center">
                        <div class="form-check d-flex justify-content-center">
                            <input class="form-check-input me-2"
                                type="checkbox"
                                value="${c.id}"
                                id="carrera-${c.id}">
                            <label class="form-check-label fw-semibold"
                                for="carrera-${c.id}">
                                ${c.nombre}
                            </label>
                        </div>
                    </div>
                </div>
            `;
    
            container.appendChild(col);
        });
    
        this.bindCarreras();
    }

    static bindCarreras() {

        const cards = this.root.querySelectorAll(".carrera-card");
    
        cards.forEach(card => {
    
            const checkbox = card.querySelector("input");
            const id = parseInt(checkbox.value);
    
            checkbox.addEventListener("change", (e) => {
    
                if (e.target.checked) {
                    this.state.carreras.add(id);
                    card.classList.add("border-primary", "bg-light");
                } else {
                    this.state.carreras.delete(id);
                    card.classList.remove("border-primary", "bg-light");
                }
    
                console.log(Array.from(this.state.carreras));
            });
    
            // Permitir click en toda la card
            card.addEventListener("click", (e) => {
                if (e.target.tagName === "INPUT" || e.target.tagName === "LABEL") {
                    return;
                }
                checkbox.click();
            });
        });
    }

    static syncCarrerasUI() {

        const cards = this.root.querySelectorAll(".carrera-card");
    
        cards.forEach(card => {
            const checkbox = card.querySelector("input");
            const id = parseInt(checkbox.value);
    
            const isSelected = this.state.carreras.has(id);
    
            checkbox.checked = isSelected;
    
            card.classList.toggle("border-primary", isSelected);
            card.classList.toggle("bg-light", isSelected);
        });
    }

    // ================================================================================= //
    //                                   ACTIVIDADES                                     //
    // ================================================================================= //

    static bindActividades() {

        const btn = this.root.querySelector("#btn-agregar-actividad");
        const container = this.root.querySelector("#actividades-container");
    
        if (!btn || !container) return;
    
        btn.addEventListener("click", () => {
            this.agregarActividadUI();
        });
    }
    
    // Crear UI de la actividad
    static agregarActividadUI(data = null) {
    
        const container = this.root.querySelector("#actividades-container");
        
        const div = document.createElement("div");
        div.className = "card p-3 mb-3 shadow-sm actividad-item";
        div.dataset.id = data?.id ?? null;
    
        div.innerHTML = `
            <div class="row g-2 align-items-end">
                <div class="col-md-6">
                    <label class="form-label">Descripción</label>
                    <input type="text" class="form-control actividad-descripcion"
                           value="${data?.descripcion ?? ""}"
                           required>
                </div>
    
                <div class="col-md-3">
                    <label class="form-label">Horas</label>
                    <input type="number" min="1"
                           class="form-control actividad-horas"
                           value="${data?.horas ?? ""}"
                           required>
                </div>
    
                <div class="col-md-3 text-end">
                    <button type="button"
                            class="btn btn-outline-danger btn-eliminar-actividad">
                        Eliminar
                    </button>
                </div>
            </div>
        `;
    
        container.appendChild(div);
    
        this.bindActividadEvents(div);
    }
    
    static bindActividadEvents(element) {
    
        const btnEliminar = element.querySelector(".btn-eliminar-actividad");
    
        btnEliminar.addEventListener("click", () => {
            element.remove();
        });
    }

    // ================================================================================= //
    //                                     CARRERAS                                      //
    // ================================================================================= //

    // Cachar elementos del frontend
    static initResponsables() {

        this.inputBuscar = this.root.querySelector("#buscar-responsable"); // buscador
        this.listaResultados = this.root.querySelector("#lista-responsables");  // resultados
        this.listaSeleccionados = this.root.querySelector("#responsables-seleccionados"); // seleccionados
    
        if (!this.inputBuscar) return;
    
        let timeout = null;
    
        this.inputBuscar.addEventListener("keyup", (e) => {
    
            const term = e.target.value.trim();
    
            clearTimeout(timeout);
    
            if (term.length < 1) {
                this.listaResultados.innerHTML = ""; // busqueda menor eliminar resultados
                return;
            }
    
            timeout = setTimeout(() => {
                this.buscarResponsables(term);
            }, 300);
        });
    
        this.renderSeleccionados();
    }

    // Peticion al backend
    static async buscarResponsables(term) {

        try {

            const response = await fetch(
                `/api/visitas/buscar?search=${encodeURIComponent(term)}&page=0&size=5`
            );
    
            const data = await response.json();
    
            this.renderResultados(data.content);
    
        } catch (error) {
            console.error("Error buscando responsables:", error);
        }
    }

    // renderizar resultados del backends
    static renderResultados(lista) {
        this.listaResultados.innerHTML = "";
    
        if (!lista || lista.length === 0) {
            this.listaResultados.innerHTML =
                `<div class="text-muted">Sin resultados</div>`;
            return;
        }
    
        lista.forEach(v => {
    
            if (this.state.responsables.has(v.id)) return; // No renderizar responsables ya seleccionados
    
            const item = document.createElement("div");
            item.className = "p-2 border rounded mb-2 small bg-white responsable-item";
            item.style.cursor = "pointer";
    
            item.innerHTML = `
                <div class="fw-semibold">
                    ${v.nombre} ${v.apellidoPaterno} ${v.apellidoMaterno ?? ""}
                </div>
                <div class="text-muted">
                    ${v.noCuentaRFC} | ${v.email ?? ""}
                </div>
            `;
    
            item.addEventListener("click", () => {
                this.state.responsables.set(v.id, v);
                console.log(this.state.responsables); // Se estan guardando en la variable
                this.listaResultados.innerHTML = ""; // Reset fragmento
                this.inputBuscar.value = "";
                this.renderSeleccionados();
            });
    
            this.listaResultados.appendChild(item);
        });
    }

    static renderSeleccionados() {

        this.listaSeleccionados.innerHTML = "";
    
        if (this.state.responsables.size === 0) {
            this.listaSeleccionados.innerHTML =
                `<div class="text-muted small">Ningún responsable seleccionado</div>`;
            return;
        }
    
        this.state.responsables.forEach((v, id) => {

            const badge = document.createElement("div");
            badge.className = "border rounded p-2 mb-2 bg-light d-flex justify-content-between align-items-center";
            badge.style.cursor = "pointer";
    
            badge.innerHTML = `
                <div>
                    <div class="fw-semibold">
                        ${v.nombre} ${v.apellidoPaterno} ${v.apellidoMaterno ?? ""}
                    </div>
                    <div class="small text-muted">
                        ${v.noCuentaRFC} | ${v.email ?? ""}
                    </div>
                </div>
                <button class="btn btn-sm btn-outline-danger">X</button>
            `;
    
            badge.querySelector("button").addEventListener("click", (e) => {
                e.stopPropagation();
                this.state.responsables.delete(id);
                console.log(this.state.responsables); // Se estan guardando en la variable
                this.renderSeleccionados();
            });
    
            this.listaSeleccionados.appendChild(badge);
        });
    }

    // ================================================================================= //
    //                                      SUBMIT                                       //
    // ================================================================================= //

    static bindSubmit() {

        const form = this.root.querySelector("#proyecto-form");
        if (!form) return;
    
        form.addEventListener("submit", async (e) => {
            e.preventDefault();
    
            const payload = this.buildPayload();

            const isEdit = this.tipo === "editar";
    
            const url = isEdit
                ? `/api/proyectos/${this.root.dataset.id}`
                : `/api/proyectos`;

            const method = isEdit ? "PUT" : "POST";
    
            try {
                const response = await fetch(url, {
                    method,
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(payload)
                });
    
                if (!response.ok) {
                    throw new Error("Error al guardar proyecto");
                }
                
                alert(isEdit
                    ? "Proyecto Actualizado Correctamente"
                    : "Proyecto Creado Correctamente"
                );
                    
                const redirectUrl = isEdit ? "/pantallas/proyectos/editar" : "/pantallas/proyectos/crear";
                await this.swapMainContent(redirectUrl);
                    
            } catch (error) {
                console.error(error);
                alert("Error al guardar proyecto");
            }
        });
    }

    // Armado JSON
    static buildPayload() {

        // 1. Campos simples
        const nombre = this.root.querySelector("[name='nombre']").value.trim();
        const descripcion = this.root.querySelector("[name='descripcion']").value.trim();
        const objetivos = this.root.querySelector("[name='objetivos']").value.trim();
        const clave = this.root.querySelector("[name='clave']").value.trim();
    
        // 2. Carreras (Set → Array)
        const carrerasIds = Array.from(this.state.carreras);
    
        // 3. Responsables (Map → Array de ids)
        const visitasIds = Array.from(this.state.responsables.keys());
    
        // 4. Actividades (leer DOM dinámicamente)
        const actividades = [];
    
        const actividadElements = this.root.querySelectorAll(".actividad-item");
    
        actividadElements.forEach(el => {
            const id = el.dataset.id ? parseInt(el.dataset.id) : null;
            const descripcion = el.querySelector(".actividad-descripcion").value.trim();
            const horas = parseInt(el.querySelector(".actividad-horas").value);
    
            if (descripcion && horas > 0) {
                actividades.push({
                    id,
                    descripcion,
                    horas
                });
            }
        });
    
        return {
            nombre,
            descripcion,
            objetivos,
            clave,
            actividades,
            visitasIds,
            carrerasIds
        };
    }

    static async swapMainContent(url) {

        const response = await fetch(url);
        const html = await response.text();
    
        const main = document.querySelector("#main-content");
        main.innerHTML = html;
    
        if (window.htmx) {
            window.htmx.process(main);
        }
        
        const newPage = document.querySelector("[data-page]");
        if (!newPage) return;

        const pageName = newPage.dataset.page;

        if (pageName === "proyecto-crear") {
            ProyectoPage.init("crear");
        }

        if (pageName === "proyecto-editar") {
            ProyectoPage.init("editar");
        }
        
    }
}



