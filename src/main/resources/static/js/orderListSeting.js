
// prices
const allPrices = document.querySelectorAll(".price");
if(allPrices){
    allPrices.forEach(priceEl =>{
        let price = priceEl.textContent.split("UAH");
        price = price[0];
        priceEl.textContent = Number(price).toFixed(2) + " UAH"
    })
}

// date
const allDates = document.querySelectorAll(".order__date");

if(allDates){
    allDates.forEach(date =>{
        let unformattedDate = date.textContent;
        let formattedDate = new Date(unformattedDate).toLocaleDateString('en-us', { weekday:"long", year:"numeric", month:"short", day:"numeric"});

        date.textContent = `${new Date(unformattedDate).toLocaleTimeString('en-us', {hour:"2-digit", minute:"2-digit"})} ${new Date(unformattedDate).toLocaleDateString('en-us', { day:"numeric"})}-${new Date(unformattedDate).toLocaleDateString('en-us', { month:"numeric"})}-${new Date(unformattedDate).toLocaleDateString('en-us', { year:"numeric"})} (${new Date(unformattedDate).toLocaleDateString('en-us', { weekday:"long"})})`
    })
}

// color of order status

const allStatuses = document.querySelectorAll(".status");
const allCancelButtons = document.querySelectorAll(".cancel-button");
if(allStatuses){
    changeStatusColor()
    setInterval(changeStatusColor, 1000)

}
function changeStatusColor(){
    allStatuses.forEach((el, i) =>{
        let statusColor = {
            opened:"#5CFF5C",
            cancelled:"red"
        }

        let statusElText = el.textContent.toLowerCase();
        el.style.color = statusColor[statusElText]
        if(statusElText === "cancelled"){
           allCancelButtons[i].style.display = "none";
        }
    })
}