const dropdownMenu = document.querySelectorAll(".dropdown-menu")

// Si el menu está vacío para anonimos o determinado perfil, agrego item indicando que no tiene acciones permitidas
dropdownMenu.forEach( menu => {
    if(menu.innerText.trim() == ""){
        menu.insertAdjacentHTML("beforeend", "<li><a class='dropdown-item' href='#'>No tiene acciones permitidas</a></li>")
    }
})