let txtEmail = document.getElementsByTagName("input")[0]
let txtsenha = document.getElementsByTagName("input")[1]
let btn = document.getElementsByTagName("input")[2]

btn.addEventListener("click", ()=>{
    if(txtEmail.value == "" || txtsenha.value == "") {
        alert("Favor preencher todos os campos!")
    }
})