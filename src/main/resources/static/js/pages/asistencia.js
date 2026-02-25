/*
export class AsistenciaActivaPage {

    static init() {
        const root = document.querySelector('[data-page="asistencia-activa"]');
        if (!root) return;
        this.cargar();
        this.bindEvents();
    }

    static cargar() {
        const root = document.querySelector('[data-page="asistencia-activa"]');
        if (!root) return;
        const equipoId = this.obtenerEquipoId();
        
        if (!equipoId) {
            console.warn('No se encontró id de equipo en la URL');
            return;
        }
        
        this.cargarAsistenciaActiva(equipoId);
    }

    static obtenerEquipoId() {
        const root = document.querySelector('[data-page="asistencia-activa"]');
        return root?.dataset.equipoId ?? null;
    }

    static cargarAsistenciaActiva(equipoId) {
        fetch(`/api/asistencias/equipo/${equipoId}/activa`)
            .then(async r => {
                if (r.status === 204) return null;
                if (!r.ok) throw new Error('Error HTTP ' + r.status);
                return r.json();
            })
            .then(data => {
                this.renderizarAsistencia(data, equipoId);
            })
            .catch(err => {
                console.error('Error cargando asistencia activa', err);
                this.asistenciaActual = null;
                this.renderizarFormulario(null, equipoId);
            });
    }

    static renderizarAsistencia(a, equipoId) {
        this.asistenciaActual = a ?? null;
        this.renderHoras(a);
        this.renderDashboardVisita(a);
        this.renderDashboardProyecto(a);
        this.renderFormulario(a, equipoId);
    }

    static renderHoras(a) {
        const cont = document.getElementById('asistencia-horas');
        if (!cont) return;

        const entrada = a?.horaEntrada ?? '';
        const salida = a?.horaSalida ?? '';
        const estatusActual = a?.equipo?.estatus?.id ?? '';
        const sinAsistencia = !a;

        cont.innerHTML = `
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Horario</h5>

                    <div class="row">
                        <div class="col">
                            <label class="form-label">Hora de entrada</label>
                            <input type="datetime-local" class="form-control"
                                value="${this.toInputDate(entrada)}"
                                ${estatusActual === 2 ? 'readonly' : ''}>
                        </div>

                        <div class="col">
                            <label class="form-label">Hora de salida</label>
                            <input type="datetime-local" class="form-control"
                                value="${this.toInputDate(salida)}"
                                ${!a ? 'readonly' : ''}>
                            <p>Estatus actual: ${estatusActual ?? 'sin asistencia'}</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    static renderDashboardVisita(a) {
        const cont = document.getElementById('dashboard-visita');
        if (!cont) return;

        const visita = a?.visita;
        if (!visita) {
            cont.innerHTML = `<p class="text-muted">Sin visita asignada</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Visita actual</h5>
                    <p><strong>Nombre:</strong> ${visita.nombre} ${visita.apellidoPaterno} ${visita.apellidoMaterno}</p>
                    <p><strong>Numero de cuenta o RFC:</strong> ${visita.noCuentaRFC}</p>
                    <p>Id visita ${visita.id}</p>
                </div>
            </div>
        `;
    }

    static onVisitaSeleccionada(visitaId) {
        fetch(`/api/visitas/${visitaId}`)
            .then(r => r.json())
            .then(visita => {
                this.visitaSeleccionada = visita;

                this.renderDashboardVisita({ visita });

                const input = document.querySelector('input[name="visitaId"]');
                if (input) input.value = visita.id;
            })
            .catch(err => console.error('Error cargando visita', err));
    }

    static renderDashboardProyecto(a) {
        const cont = document.getElementById('dashboard-proyecto');
        if (!cont) return;

        const act = a?.actividad;
        if (!act) {
            cont.innerHTML = `<p class="text-muted">Sin actividad seleccionada</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Actividad seleccionada</h5>
                    <p><strong>Descripción:</strong> ${act.descripcion}</p>
                    <p><strong>Horas:</strong> ${act.horas}</p>
                </div>
            </div>
        `;
    }

    static onProyectoSeleccionado(proyectoId) {
        fetch(`/api/proyectos/${proyectoId}`)
            .then(r => r.json())
            .then(proyecto => {
                this.proyectoSeleccionado = proyecto;
                this.actividadSeleccionada = null;

                this.renderSelectorActividades(proyecto.actividades);
                this.renderDashboardProyecto(null);

                const inputProyecto = document.querySelector('input[name="proyectoId"]');
                if (inputProyecto) inputProyecto.value = proyecto.id;
            })
            .catch(err => console.error('Error cargando proyecto', err));
    }

    static renderSelectorActividades(actividades) {
        const cont = document.getElementById('selector-actividad');
        if (!cont) return;

        if (!actividades || actividades.length === 0) {
            cont.innerHTML = `<p class="text-muted">Proyecto sin actividades</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Seleccionar actividad</h5>

                    <select id="actividad-select" class="form-select">
                        <option value="">-- Selecciona una actividad --</option>
                        ${actividades.map(a => `
                            <option value="${a.id}">
                                ${a.descripcion} (${a.horas} h)
                            </option>
                        `).join('')}
                    </select>
                </div>
            </div>
        `;

        document
            .getElementById('actividad-select')
            .addEventListener('change', e => {
                const actId = e.target.value;
                if (!actId) return;

                const act = actividades.find(a => a.id == actId);
                this.onActividadSeleccionada(act);
            });
    }

    static onActividadSeleccionada(actividad) {
        this.actividadSeleccionada = actividad;

        this.renderDashboardProyecto({ actividad });

        const input = document.querySelector('input[name="actividadId"]');
        if (input) input.value = actividad.id;
    }

    static renderFormulario(a, equipoId) {
        const cont = document.getElementById('asistencia-form');
        if (!cont) return;

        const observacion = a?.observacion ?? '';
        const estatusActual = a?.equipo?.estatus?.id ?? 1;

        console.log("Estatus actual: ", estatusActual);

        const transiciones = {
            1: [2, 3], // Disponible -> En uso, Reservado
            2: [1],    // En uso -> Disponible
            3: [2]     // Reservado -> En uso
        };

        const estados = {
            1: 'Disponible',
            2: 'En uso',
            3: 'Reservado'
        };

        const estadosPermitidos = transiciones[estatusActual] ?? [];

        // Generar options dinámicamente
        const optionsHTML = estadosPermitidos.map(id => `
            <option value="${id}">
                ${estados[id]}
            </option>
        `).join('');

        const esDisponible = Number(estatusActual) === 1;

        const botonAccionHTML = esDisponible
            ? `<button type="button" id="btn-accion-secundaria"
                class="btn btn-secondary w-100">
                    Volver
            </button>`
            : `<button type="button" id="btn-accion-secundaria"
                class="btn btn-danger w-100">
                    Eliminar asistencia
            </button>`;


        cont.innerHTML = `
            <form id="form-asistencia" class="card shadow-sm">
                <input type="hidden" name="visitaId" value="${a?.visita?.id ?? ''}">
                <input type="hidden" name="proyectoId" value="${a?.proyecto?.id ?? ''}">
                <input type="hidden" name="actividadId" value="${a?.actividad?.id ?? ''}">


                <div class="card-body">
                    <h5 class="card-title">Guardar asistencia</h5>

                    <input type="hidden" name="equipoId" value="${equipoId}">

                    <div class="mb-3">
                        <label class="form-label">Observaciones</label>
                        <textarea name="observacion"
                            class="form-control">${observacion}</textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Estatus del equipo</label>
                        <select name="estatusEquipo" class="form-select">
                            ${optionsHTML}
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 mb-2">
                        Guardar asistencia
                    </button>

                    ${botonAccionHTML}

                </div>
            </form>
        `;

        document
            .getElementById('form-asistencia')
            .addEventListener('submit', e => {
                e.preventDefault();
                this.guardarAsistencia();
            });
            document
                .getElementById('btn-accion-secundaria')
                ?.addEventListener('click', () => {

                    if (esDisponible) {
                        htmx.ajax('GET',
                            '/pantallas/asistencias/principal',
                            { target: '#main-content', swap: 'innerHTML' }
                        );
                        return;
                    }
                    
                    const asistenciaId = this.asistenciaActual?.id;
                    if (!asistenciaId) return;

                    fetch(`/api/asistencias/${asistenciaId}`, {
                        method: 'DELETE'
                    })
                    .then(r => {
                        if (!r.ok) throw new Error('Error eliminando asistencia');
                        return fetch(
                            `/api/equipos/estatus?equipoId=${equipoId}&estatusId=1`, 
                            { method: 'PATCH' }
                        );
                    })
                    .then(r => {
                        if (!r.ok) throw new Error('Error actualizando estatus del equipo');
                    })
                    .then(() => {
                        return htmx.ajax('GET',
                            '/pantallas/asistencias/principal',
                            { target: '#main-content', swap: 'innerHTML' }
                        );
                    })
                    .catch(err => console.error(err));
                });

    }

    static toInputDate(iso) {
        if (!iso) return '';
        return iso.substring(0, 16);
    }

    static guardarAsistencia() {

        const inputsHora = document.querySelectorAll(
            '#asistencia-horas input[type="datetime-local"]'
        );

        const estatusEquipo = Number(
            document.querySelector('[name="estatusEquipo"]')?.value
        );


        const dto = {
            horaEntrada: inputsHora[0]?.value || null,
            horaSalida: inputsHora[1]?.value || null,
            observacion: document.querySelector('[name="observacion"]').value,
            visitaId: Number(document.querySelector('[name="visitaId"]').value),
            equipoId: Number(document.querySelector('[name="equipoId"]').value),
            actividadId: Number(document.querySelector('[name="actividadId"]').value)
        };

        const asistenciaId = this.asistenciaActual?.id;

        const method = asistenciaId ? 'PUT' : 'POST';
        const url = asistenciaId
            ? `/api/asistencias/${asistenciaId}`
            : `/api/asistencias`;

        fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        })
        .then(r => {
            if (!r.ok) throw new Error('Error guardando asistencia');
            return r.json();
        })
        .then(a => {
            this.asistenciaActual = a;

            return fetch(
                `/api/equipos/estatus?equipoId=${dto.equipoId}&estatusId=${estatusEquipo}`,
                { method: 'PATCH' }
            );
        })
        .then(() => {
            htmx.ajax('GET',
                '/pantallas/asistencias/principal',
                { target: '#main-content', swap: 'innerHTML' }
            );
        })

        .catch(err => console.error(err));
    }
    
    static bindEvents() {
        if (!this.root) return;

        this.root.addEventListener('click', (e) =>  {
            const proyectoBtn = e.target.closest('[hx-get*="/pantallas/proyectos/"]');
            if (proyectoBtn) {
                const url = proyectoBtn.getAttribute('hx-get');
                const params = new URLSearchParams(url.split('?')[1]);
                const proyectoId = params.get('id');
                if (!proyectoId) return;

                e.preventDefault();
                this.onProyectoSeleccionado(proyectoId);
            }
            
            const visitaBtn = e.target.closest('button[hx-get*="/pantallas/visitas/"]');
            if (visitaBtn) {
                const url = visitaBtn.getAttribute('hx-get');
                const params = new URLSearchParams(url.split('?')[1]);
                const visitaId = params.get('id');

                e.preventDefault();
                this.onVisitaSeleccionada(visitaId);
            }
        });
    }
}

*/

export class AsistenciaActivaPage {

    static init() {
        console.log("INIT AsistenciaActivaPage");
        this.root = document.querySelector('[data-page="asistencia-activa"]');
        console.log("ROOT:", this.root);
        if (!this.root) return;
        this.cargar();
        this.bindEvents();
    }

    static cargar() {
        if (!this.root) return;
        const equipoId = this.obtenerEquipoId();
        
        if (!equipoId) {
            console.warn('No se encontró id de equipo en la URL');
            return;
        }
        
        this.cargarAsistenciaActiva(equipoId);
    }

    static obtenerEquipoId() {
        return this.root?.dataset.equipoId ?? null;
    }

    static cargarAsistenciaActiva(equipoId) {
        fetch(`/api/asistencias/equipo/${equipoId}/activa`)
            .then(async r => {
                if (r.status === 204) return null;
                if (!r.ok) throw new Error('Error HTTP ' + r.status);
                return r.json();
            })
            .then(data => {
                this.renderizarAsistencia(data, equipoId);
            })
            .catch(err => {
                console.error('Error cargando asistencia activa', err);
                this.asistenciaActual = null;
                this.renderizarFormulario(null, equipoId);
            });
    }

    static renderizarAsistencia(a, equipoId) {
        this.asistenciaActual = a ?? null;
        this.renderHoras(a);
        this.renderDashboardVisita(a);
        this.renderDashboardProyecto(a);
        this.renderFormulario(a, equipoId);
    }

    static renderHoras(a) {
        const cont = this.root.querySelector('#asistencia-horas');
        if (!cont) return;

        const entrada = a?.horaEntrada ?? '';
        const salida = a?.horaSalida ?? '';
        const estatusActual = a?.equipo?.estatus?.id ?? '';
        const sinAsistencia = !a;

        cont.innerHTML = `
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Horario</h5>

                    <div class="row">
                        <div class="col">
                            <label class="form-label">Hora de entrada</label>
                            <input type="datetime-local" class="form-control"
                                value="${this.toInputDate(entrada)}"
                                ${estatusActual === 2 ? 'readonly' : ''}>
                        </div>

                        <div class="col">
                            <label class="form-label">Hora de salida</label>
                            <input type="datetime-local" class="form-control"
                                value="${this.toInputDate(salida)}"
                                ${!a ? 'readonly' : ''}>
                            <p>Estatus actual: ${estatusActual ?? 'sin asistencia'}</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    static renderDashboardVisita(a) {
        const cont = this.root.querySelector('#dashboard-visita');
        if (!cont) return;

        const visita = a?.visita;
        if (!visita) {
            cont.innerHTML = `<p class="text-muted">Sin visita asignada</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Visita actual</h5>
                    <p><strong>Nombre:</strong> ${visita.nombre} ${visita.apellidoPaterno} ${visita.apellidoMaterno}</p>
                    <p><strong>Numero de cuenta o RFC:</strong> ${visita.noCuentaRFC}</p>
                    <p>Id visita ${visita.id}</p>
                </div>
            </div>
        `;
    }

    static onVisitaSeleccionada(visitaId) {
        fetch(`/api/visitas/${visitaId}`)
            .then(r => r.json())
            .then(visita => {
                this.visitaSeleccionada = visita;

                this.renderDashboardVisita({ visita });

                const input = this.root.querySelector('input[name="visitaId"]');
                if (input) input.value = visita.id;
            })
            .catch(err => console.error('Error cargando visita', err));
    }

    static renderDashboardProyecto(a) {
        const cont = this.root.querySelector('#dashboard-proyecto');
        if (!cont) return;

        const act = a?.actividad;
        if (!act) {
            cont.innerHTML = `<p class="text-muted">Sin actividad seleccionada</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Actividad seleccionada</h5>
                    <p><strong>Descripción:</strong> ${act.descripcion}</p>
                    <p><strong>Horas:</strong> ${act.horas}</p>
                </div>
            </div>
        `;
    }

    static onProyectoSeleccionado(proyectoId) {
        fetch(`/api/proyectos/${proyectoId}`)
            .then(r => r.json())
            .then(proyecto => {
                this.proyectoSeleccionado = proyecto;
                this.actividadSeleccionada = null;

                this.renderSelectorActividades(proyecto.actividades);
                this.renderDashboardProyecto(null);

                const inputProyecto = this.root.querySelector('input[name="proyectoId"]');
                if (inputProyecto) inputProyecto.value = proyecto.id;
            })
            .catch(err => console.error('Error cargando proyecto', err));
    }

    static renderSelectorActividades(actividades) {
        const cont = this.root.querySelector('#selector-actividad');
        if (!cont) return;

        if (!actividades || actividades.length === 0) {
            cont.innerHTML = `<p class="text-muted">Proyecto sin actividades</p>`;
            return;
        }

        cont.innerHTML = `
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Seleccionar actividad</h5>

                    <select id="actividad-select" class="form-select">
                        <option value="">-- Selecciona una actividad --</option>
                        ${actividades.map(a => `
                            <option value="${a.id}">
                                ${a.descripcion} (${a.horas} h)
                            </option>
                        `).join('')}
                    </select>
                </div>
            </div>
        `;

        this.root
            .querySelector('#actividad-select')
            .addEventListener('change', e => {
                const actId = e.target.value;
                if (!actId) return;

                const act = actividades.find(a => a.id == actId);
                this.onActividadSeleccionada(act);
            });
    }

    static onActividadSeleccionada(actividad) {
        this.actividadSeleccionada = actividad;

        this.renderDashboardProyecto({ actividad });

        const input = this.root.querySelector('input[name="actividadId"]');
        if (input) input.value = actividad.id;
    }

    static renderFormulario(a, equipoId) {
        const cont = this.root.querySelector('#asistencia-form');
        if (!cont) return;

        const observacion = a?.observacion ?? '';
        const estatusActual = a?.equipo?.estatus?.id ?? 1;

        console.log("Estatus actual: ", estatusActual);

        const transiciones = {
            1: [2, 3], // Disponible -> En uso, Reservado
            2: [1],    // En uso -> Disponible
            3: [2]     // Reservado -> En uso
        };

        const estados = {
            1: 'Disponible',
            2: 'En uso',
            3: 'Reservado'
        };

        const estadosPermitidos = transiciones[estatusActual] ?? [];

        // Generar options dinámicamente
        const optionsHTML = estadosPermitidos.map(id => `
            <option value="${id}">
                ${estados[id]}
            </option>
        `).join('');

        const esDisponible = Number(estatusActual) === 1;

        const botonAccionHTML = esDisponible
            ? `<button type="button" id="btn-accion-secundaria"
                class="btn btn-secondary w-100">
                    Volver
            </button>`
            : `<button type="button" id="btn-accion-secundaria"
                class="btn btn-danger w-100">
                    Eliminar asistencia
            </button>`;


        cont.innerHTML = `
            <form id="form-asistencia" class="card shadow-sm">
                <input type="hidden" name="visitaId" value="${a?.visita?.id ?? ''}">
                <input type="hidden" name="proyectoId" value="${a?.proyecto?.id ?? ''}">
                <input type="hidden" name="actividadId" value="${a?.actividad?.id ?? ''}">


                <div class="card-body">
                    <h5 class="card-title">Guardar asistencia</h5>

                    <input type="hidden" name="equipoId" value="${equipoId}">

                    <div class="mb-3">
                        <label class="form-label">Observaciones</label>
                        <textarea name="observacion"
                            class="form-control">${observacion}</textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Estatus del equipo</label>
                        <select name="estatusEquipo" class="form-select">
                            ${optionsHTML}
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 mb-2">
                        Guardar asistencia
                    </button>

                    ${botonAccionHTML}

                </div>
            </form>
        `;

        this.root
            .querySelector('#form-asistencia')
            .addEventListener('submit', e => {
                e.preventDefault();
                this.guardarAsistencia();
            });
            this.root
                .querySelector('#btn-accion-secundaria')
                ?.addEventListener('click', () => {

                    if (esDisponible) {
                        htmx.ajax('GET',
                            '/pantallas/asistencias/principal',
                            { target: '#main-content', swap: 'innerHTML' }
                        );
                        return;
                    }
                    
                    const asistenciaId = this.asistenciaActual?.id;
                    if (!asistenciaId) return;

                    fetch(`/api/asistencias/${asistenciaId}`, {
                        method: 'DELETE'
                    })
                    .then(r => {
                        if (!r.ok) throw new Error('Error eliminando asistencia');
                        return fetch(
                            `/api/equipos/estatus?equipoId=${equipoId}&estatusId=1`, 
                            { method: 'PATCH' }
                        );
                    })
                    .then(r => {
                        if (!r.ok) throw new Error('Error actualizando estatus del equipo');
                    })
                    .then(() => {
                        return htmx.ajax('GET',
                            '/pantallas/asistencias/principal',
                            { target: '#main-content', swap: 'innerHTML' }
                        );
                    })
                    .catch(err => console.error(err));
                });

    }

    static toInputDate(iso) {
        if (!iso) return '';
        return iso.substring(0, 16);
    }

    static guardarAsistencia() {

        const inputsHora = this.root.querySelectorAll(
            '#asistencia-horas input[type="datetime-local"]'
        );

        const estatusEquipo = Number(
            this.root.querySelector('[name="estatusEquipo"]')?.value
        );


        const dto = {
            horaEntrada: inputsHora[0]?.value || null,
            horaSalida: inputsHora[1]?.value || null,
            observacion: this.root.querySelector('[name="observacion"]').value,
            visitaId: Number(this.root.querySelector('[name="visitaId"]').value),
            equipoId: Number(this.root.querySelector('[name="equipoId"]').value),
            actividadId: Number(this.root.querySelector('[name="actividadId"]').value)
        };

        const asistenciaId = this.asistenciaActual?.id;

        const method = asistenciaId ? 'PUT' : 'POST';
        const url = asistenciaId
            ? `/api/asistencias/${asistenciaId}`
            : `/api/asistencias`;

        fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        })
        .then(r => {
            if (!r.ok) throw new Error('Error guardando asistencia');
            return r.json();
        })
        .then(a => {
            this.asistenciaActual = a;

            return fetch(
                `/api/equipos/estatus?equipoId=${dto.equipoId}&estatusId=${estatusEquipo}`,
                { method: 'PATCH' }
            );
        })
        .then(() => {
            htmx.ajax('GET',
                '/pantallas/asistencias/principal',
                { target: '#main-content', swap: 'innerHTML' }
            );
        })

        .catch(err => console.error(err));
    }
    
    static bindEvents() {
        if (!this.root) return;

        this.root.addEventListener('click', (e) =>  {
            const proyectoBtn = e.target.closest('[hx-get*="/pantallas/proyectos/"]');
            if (proyectoBtn) {
                const url = proyectoBtn.getAttribute('hx-get');
                const params = new URLSearchParams(url.split('?')[1]);
                const proyectoId = params.get('id');
                if (!proyectoId) return;

                e.preventDefault();
                this.onProyectoSeleccionado(proyectoId);
                return;
            }
            
            const visitaBtn = e.target.closest('button[hx-get*="/pantallas/visitas/"]');
            if (visitaBtn) {
                const url = visitaBtn.getAttribute('hx-get');
                const params = new URLSearchParams(url.split('?')[1]);
                const visitaId = params.get('id');

                e.preventDefault();
                this.onVisitaSeleccionada(visitaId);
            }
        });
    }
}
