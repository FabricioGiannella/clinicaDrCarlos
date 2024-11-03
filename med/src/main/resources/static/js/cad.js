let txtNome = document.getElementsByTagName("input")[0]
        let txtTel = document.getElementsByTagName("input")[1]
        let txtEmail = document.getElementsByTagName("input")[2]
        let txtsenha = document.getElementsByTagName("input")[3]
        let btn = document.getElementsByTagName("input")[4]

        function validaEmail(email) {
            let arroba = false
            for(let x of email) {
                if(x == "@") {
                    arroba = true
                }
            }
            return arroba
        }

        btn.addEventListener("click", ()=>{
            if(txtNome.value == "") {
                alert("Campo nome está vazio!")
            }
            else if(txtNome.value.length < 8) {
                alert("Nome tem que ter no mínimo 8 letras!")
            }
            else if(txtTel.value == "") {
                alert("Campo Telefone está vazio!")
            }
            else if(txtTel.value.length < 8) {
                alert("Telefone não pode ter menos que 8 números!")
            }
            else if(txtEmail.value == "") {
                alert("Campo email está vazio!")
            }
            else if(!validaEmail(txtEmail.value)) {
                alert("Email inválido!")
            }
            else if(txtsenha.value == "") {
                alert("Campo senha está vazio!")
            }
            else if(txtsenha.value.length < 3) {
                alert("Senha tem que ter no mínimo 3 caracteres!")
            }
        })