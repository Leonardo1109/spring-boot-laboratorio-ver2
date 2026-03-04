export class EquiposPage {
    static init() {
        this.inicializarFiltros();
        this.cargar();
    }

    static inicializarFiltros() {
        const contenedor = document.getElementById("filtros-equipos");
        if (!contenedor) return;
    
        contenedor.addEventListener("click", (e) => {
            const btn = e.target.closest("button");
            if (!btn) return;
    
            const filtro = btn.dataset.filtro;
            this.aplicarFiltro(filtro);
    
            // Opcional: marcar botón activo visualmente
            contenedor.querySelectorAll("button").forEach(b => {
                b.classList.remove("active");
                if (b.dataset.filtro === "todos") {
                    b.classList.remove("btn-dark");
                    b.classList.add("btn-light");
                } else {
                    const color = this.estilosPorEstatus[b.dataset.filtro]?.border;
                    if (color) {
                        b.classList.remove(`btn-${color}`);
                        b.classList.add(`btn-outline-${color}`);
                    }
                }
            });
            
            btn.classList.add("active");
            
            if (btn.dataset.filtro === "todos") {
                btn.classList.remove("btn-light");
                btn.classList.add("btn-dark");
            } else {
                const color = this.estilosPorEstatus[btn.dataset.filtro]?.border;
                if (color) {
                    btn.classList.remove(`btn-outline-${color}`);
                    btn.classList.add(`btn-${color}`);
                }
            }
        });
    }

    static aplicarFiltro(filtro) {
        const secciones = {
            1: "equipos-disponible",
            2: "equipos-en-uso",
            3: "equipos-reservado",
            4: "equipos-fuera-servicio",
            5: "equipos-en-mantenimiento"
        };
    
        Object.entries(secciones).forEach(([estatus, id]) => {
            const contenedor = document.getElementById(id);
            if (!contenedor) return;
    
            const titulo = contenedor.previousElementSibling;
    
            if (filtro === "todos") {
                contenedor.style.display = "flex";
                if (titulo) titulo.style.display = "block";
            } else {
                const visible = filtro === estatus;
                contenedor.style.display = visible ? "flex" : "none";
                if (titulo) titulo.style.display = visible ? "block" : "none";
            }
        });
    }

    static cargar() {
        fetch('/api/equipos')
            .then(r => r.json())
            .then(data => this.renderizarPorEstado(data))
            .catch(err => console.error('Error al cargar equipos', err));
    }

    static estilosPorEstatus = {
        1: { badge: 'bg-success', label: 'Disponible', border: 'success'},
        2: { badge: 'bg-primary', label: 'En uso',  border: 'primary'},
        3: { badge: 'bg-warning text-dark', label: 'Reservado',  border: 'warning' },
        4: { badge: 'bg-danger', label: 'Fuera de servicio', border: 'danger' },
        5: { badge: 'bg-info text-dark', label: 'En mantenimiento', border: 'info' }
    };

    // Renderiza los equipos en listas según su estado
    static renderizarPorEstado(equipos) {
        // Diccionario con id_estatus → id de UL
        const estadosMap = {
            1: 'equipos-disponible',
            2: 'equipos-en-uso',
            3: 'equipos-reservado',
            4: 'equipos-fuera-servicio',
            5: 'equipos-en-mantenimiento'
        };

        // Limpiar todas las listas
        Object.values(estadosMap).forEach(id => {
            const ul = document.getElementById(id);
            if (ul) ul.innerHTML = '';
        });
        

        // Recorrer cada equipo y agregarlo a la lista correspondiente
        equipos.forEach(e => {
            const ulId = estadosMap[e.estatus.id];
            const ul = document.getElementById(ulId);
            if (!ul) return;

            const ruta = this.obtenerRutaPorEstatus(e.estatus.id, e.id);

            const mensajeButton = (e.estatus.id >= 1 && e.estatus.id <= 3) 
                ? "Registrar Asistencia"
                : "Cambiar Estado";
            
            const estilo = this.estilosPorEstatus[e.estatus.id];

            const card = document.createElement('div');
            card.className = 'col-md-6 col-lg-4';

            // <div class="card shadow-sm h-100 border-${estilo.border} border-2">
            card.innerHTML = `
                <div class="card shadow-sm h-100">
                    <div class="card-body d-flex flex-column">
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <h6 class="card-title mb-0">Equipo #${e.id}</h6>
                            <span class="badge ${estilo.badge}">
                                ${estilo.label}
                            </span>
                        </div>

                        <p class="small mb-1"><strong>Código:</strong> ${e.codigoInventario}</p>
                        <p class="small mb-1"><strong>Descripción:</strong> ${e.descripcion}</p>
                        <p class="small mb-1"><strong>Ubicación:</strong> ${e.ubicacion}</p>
                        <p class="small mb-3"><strong>Tipo:</strong> ${e.tipoEquipo.descripcion}</p>

                        <div class="mt-auto text-end">
                            <button 
                                class="btn btn-custom-primary btn-sm"
                                hx-get="${ruta}" 
                                hx-target="#main-content"
                                hx-swap="innerHTML">
                                ${mensajeButton}
                            </button>
                        </div>
                    </div>
                </div>
            `;
            
            ul.appendChild(card);
            htmx.process(card);

        });
    }

    static obtenerRutaPorEstatus(estatusId, equipoId) {
        if (estatusId >= 1 && estatusId <= 3) {
            return `/pantallas/asistencias/registrar?id=${equipoId}`;
        }
        if (estatusId === 4 || estatusId === 5) {
            return `/pantallas/asistencias/cambiar-estado?id=${equipoId}`;
        }
        return '#';
    }
}