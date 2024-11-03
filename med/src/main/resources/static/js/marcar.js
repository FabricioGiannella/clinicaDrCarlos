let pegaID = document.getElementById("passaID")
        let selecionada = document.getElementById("selecionada")
        let disponivel = [...document.getElementsByClassName("true")]
        let btn = document.getElementById("enviar")
        btn.style.display = "none"
        selecionada.style.display = "none"
        for(let x of disponivel) {
            x.addEventListener("click", (e)=>{
                btn.style.display = "inline"
                selecionada.style.display = "block"
                pegaID.value = e.target.id
                selecionada.innerHTML = e.target.innerHTML
            })
        }