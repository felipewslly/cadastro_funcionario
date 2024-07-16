const formulario = document.querySelector("form");
const inputName = document.querySelector(".firstName");
const inputLastName = document.querySelector(".lastName");
const inputEmail = document.querySelector(".email");
const inputSalary = document.querySelector(".salary");
const inputOffice = document.querySelector(".office");
const messageElement = document.getElementById("message")

function cadastrar(){

    fetch("http://localhost:8080/api/employees",
    {

        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        
        method: "POST",
        body: JSON.stringify({
            firstName: inputName.value,
            lastName: inputLastName.value,
            email: inputEmail.value,
            salary: inputSalary.value,
            office: inputOffice.value,
        })


    })

    .then(function (res) {
        if(res.ok){
            messageElement.textContent = "Funcionário cadastrado com sucesso! ";
            messageElement.style.color = "green";
            limpar();
        }else{
            messageElement.textContent = "Erro ao cadastrar funcionário";
            messageElement.style.color = "red";
        }

    })
    .catch(function (res) {
        if(res.ok){
            messageElement.textContent = "Erro ao cadastrar funcionário";
            messageElement.style.color = "red";
            console.error(res);
        }
    })

};


function limpar (){
    inputName.value = "",
    inputLastName.value = "",
    inputEmail.value = "",
    inputSalary.value = "",
    inputOffice.value = ""
};

formulario.addEventListener('submit', function(event){

    event.preventDefault();

    cadastrar();
    limpar();

});

