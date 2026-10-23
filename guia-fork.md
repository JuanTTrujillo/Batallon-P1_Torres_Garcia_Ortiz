# Trabajar con un Fork en GitHub

## ¿Qué es un fork?

Un **fork** es una **copia de un repositorio ajeno en tu propia cuenta de GitHub**. El repositorio original (el del profesor) sigue intacto; tú trabajas sobre tu copia con total libertad.

| Concepto | Qué es | Dónde vive |
|---|---|---|
| **Repositorio original** | El repo del profesor | GitHub, cuenta del profesor |
| **Fork** | La copia del repo de tu grupo | GitHub, cuenta de un integrante |
| **Clon** | El fork descargado | El computador de cada integrante |

> Fork ≠ clone. El **fork** se hace en GitHub (copia en la nube). El **clone** descarga un repositorio a tu máquina. Primero se hace el fork, luego cada integrante clona **ese fork**.

---

## Organización del grupo

- **Un solo fork por grupo.** Lo hace **un integrante** (el responsable del grupo) en su cuenta.
- **Una sola rama de trabajo para todo el grupo**, llamada `desarrollo`. El responsable la crea y la sube; los demás la descargan y trabajan en ella.
- Los demás integrantes **no hacen fork**: clonan el fork del responsable.

---

## Qué debe hacer el responsable del grupo

### 1. Hacer el fork
En la página del repositorio original, clic en **Fork** → selecciona tu cuenta → **Create fork**.

### 2. Agregar a los compañeros como colaboradores
Sin esto, los demás no pueden subir cambios.
En tu fork: **Settings → Collaborators → Add people** → escribe el usuario de GitHub de cada compañero.
Cada compañero debe **aceptar la invitación** que le llega al correo (o en github.com/notifications).

### 3. Clonar el fork
```bash
git clone https://github.com/USUARIO_RESPONSABLE/nombre-repo.git
cd nombre-repo
```

### 4. Crear la rama del grupo y subirla
```bash
git checkout -b desarrollo
git push -u origin desarrollo
```
Avisa al grupo que la rama `desarrollo` ya está en GitHub.

---

## Qué deben hacer los demás integrantes

### 1. Aceptar la invitación de colaborador
Revisa tu correo o github.com/notifications y acepta.

### 2. Clonar el fork del grupo (no el original, no uno propio)
```bash
git clone https://github.com/USUARIO_RESPONSABLE/nombre-repo.git
cd nombre-repo
```

### 3. Descargar la rama del grupo
```bash
git fetch origin
git checkout desarrollo
```
Verifica que estás en ella:
```bash
git branch   # debe aparecer * desarrollo
```

---

## Flujo de trabajo diario (todos los integrantes)

Siempre en la rama `desarrollo`:

```bash
# 1. ANTES de empezar: traer lo que subieron tus compañeros
git pull origin desarrollo

# 2. Trabajar: editas, pruebas...

# 3. Guardar tus cambios
git add .
git commit -m "Agrega validación de usuario"

# 4. Traer de nuevo por si alguien subió algo mientras trabajabas
git pull origin desarrollo

# 5. Subir tus cambios
git push origin desarrollo
```

> **Si aparece un conflicto** (dos personas cambiaron las mismas líneas): abre el archivo, busca las marcas `<<<<<<<`, `=======`, `>>>>>>>`, deja el código correcto, borra las marcas y luego:
> ```bash
> git add .
> git commit -m "Resuelve conflicto en Usuario.java"
> git push origin desarrollo
> ```
> Para evitarlos: repártanse el trabajo por archivos o clases y hagan `pull` seguido.

---

## Entregar

El responsable une la rama `desarrollo` a `main` y la sube:
```bash
git checkout main
git pull origin main
git merge desarrollo
git push origin main
```
Se entrega el **enlace del fork del grupo** (`https://github.com/USUARIO_RESPONSABLE/nombre-repo`). Verifiquen en GitHub que todo el trabajo esté en `main` antes de enviarlo.

---

## Qué NO deben hacer

- ❌ **Que cada integrante haga su propio fork**: el grupo trabaja en un solo fork.
- ❌ **Clonar el repositorio original** y trabajar ahí: no tienen permisos para subir.
- ❌ **Trabajar directamente en `main`**: todo se hace en `desarrollo` y se une a `main` al entregar.
- ❌ **Empezar a trabajar sin hacer `git pull`**: es la causa número uno de conflictos.
- ❌ **Usar `git push --force`**: borra el trabajo que subieron tus compañeros.
- ❌ **Subir credenciales o archivos sensibles** (`.env`, contraseñas, claves de API). Revisen el `.gitignore`.
- ❌ **Subir carpetas generadas** (`node_modules/`, `target/`, `build/`, `.idea/`).
- ❌ **Borrar y volver a crear el fork** para "arreglar" algo: se pierde el historial y el profesor no ve el progreso.
- ❌ **Commits gigantes con mensajes vacíos** ("cambios", "asdf"): commits pequeños y con mensajes claros.
- ❌ **Que uno solo suba todo**: el historial muestra qué aportó cada integrante.
- ❌ **Subir todo el último día**: el historial de commits muestra cómo trabajaron.

---

## Resumen rápido

```
Responsable:  Fork → Agregar colaboradores → Clone → Crear rama desarrollo → Push
Integrantes:  Aceptar invitación → Clone del fork → fetch + checkout desarrollo
Todos:        pull → trabajar → commit → pull → push   (repetir)
Entrega:      merge desarrollo → main → push → entregar enlace del fork
```