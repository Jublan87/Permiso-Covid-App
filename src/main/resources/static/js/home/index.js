document.addEventListener("DOMContentLoaded", () => {
    bgImage = new Image(); 
    bgImage.src = "/imgs/index.jpg";
    bgImage.onload = () => {
        const headerBg = document.querySelector('.header-bg')
        headerBg.style.backgroundImage = "url('" + bgImage.attributes.src.nodeValue + "')"
        headerBg.classList.add('show')
    }
})