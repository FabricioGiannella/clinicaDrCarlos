let btnGerar = document.getElementById("btn1")
let dataSis = document.getElementsByTagName("input")[0]
let horaSis = document.getElementsByTagName("select")[0]
let vis = document.getElementsByTagName("span")[0]
let horCons = document.getElementById("hor")
let btnSub = document.getElementById("subm")
 btnSub.style.display = "none"
        
btnGerar.addEventListener("click", ()=>{
    let atual = new Date()
    let dataSisArray = dataSis.value.split("-")
    let ano = dataSisArray[0]
    let mes = dataSisArray[1]-1
    let dia = dataSisArray[2]
    let hora = horaSis.value

    if(ano == undefined || mes == undefined || dia == undefined) {
        alert("Favor selecionar o campo data!")
    }
    else if(ano < atual.getFullYear() || mes < atual.getMonth()) {
        alert("Ano e mês não podem ser inferiores ao atual!")
    }
    else {
        btnSub.style.display = "inline"
        let horario = dia+"/"+(mes+1)+"/"+ano+" "+hora
        vis.innerHTML = horario
        horCons.value = horario
    }
})

btnSub.addEventListener("click", ()=>{
    btnSub.style.display = "none"
})