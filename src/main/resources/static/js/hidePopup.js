const overlay = document.querySelector(".overlay")
const popupEl = document.querySelector(".popup");
const closeBtn = document.querySelector(".close");

closeBtn.addEventListener("click", (e)=>{
    popupEl.classList.add("hidden");
    overlay.classList.add("hidden")
})