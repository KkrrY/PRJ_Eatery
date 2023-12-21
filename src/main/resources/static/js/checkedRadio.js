const allRadioInputs = document.querySelectorAll("input[type='radio']");
const dishesChoices = document.querySelectorAll(".dishes__choice");

allRadioInputs.forEach((el, i)=>{
    el.addEventListener("change", (e)=>{
        dishesChoices.forEach(el =>{
            el.classList.remove("dishes__choice--active")
        })
        if(e.target.checked){
            dishesChoices[i].classList.add("dishes__choice--active")
        }else{
            dishesChoices[i].classList.remove("dishes__choice--active")
        }
    })

})
