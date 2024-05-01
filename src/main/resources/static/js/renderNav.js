const loginLink = document.querySelector("#loginLink")
const logoutLink = document.querySelector("#logoutLink")
const registrationLink = document.querySelector("#registrationLink");
const history = document.querySelector("#history");
renderNavigation();
setInterval(renderNavigation, 1000000 )

function renderNavigation(){
     fetch("/user-role").then(resp => resp.json()).then(data =>{
          console.log(data);
          if(data == true){
               loginLink.classList.add("hidden");
               registrationLink.classList.add("hidden")
               if(logoutLink.classList.contains("hidden")){
                    logoutLink.classList.remove("hidden")
                    history.classList.remove("hidden")
               }
          }
          else{
               logoutLink.classList.add("hidden")
               history.classList.add("hidden")
               loginLink.classList.remove("hidden")
               registrationLink.classList.remove("hidden")
          }

     })
}



