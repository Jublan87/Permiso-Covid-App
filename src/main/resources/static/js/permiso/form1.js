const selectPermiso = document.querySelector(".select-permiso")
const groupPatente = document.querySelector(".group-patente")
const patenteField = document.querySelector("#dominio")

const checkVisible = () => {
    if(selectPermiso.value === "false"){
        groupPatente.classList.add("visible")
    } else {
        groupPatente.classList.remove("visible")
        patenteField.value = ""
    }
}

selectPermiso.addEventListener("change", e => {
    checkVisible()
})

window.addEventListener("load", checkVisible())