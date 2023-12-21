const passwordInput = document.querySelectorAll(".password__input");
const showPasswordIcon = document.querySelectorAll(".showPass");


showPasswordIcon.forEach((btn, i) =>{
    btn.addEventListener("click", (e)=>{
        let unlockClass = "fa-unlock";
        e.target.classList.toggle(unlockClass);

        if(e.target.classList.contains(unlockClass)){
            passwordInput[i].type = "text"
        }
        else{
            passwordInput[i].type = "password"
        }
    })
})
