

//Função visualizar senha
function visualizar() {

    let senha = document.getElementById("password")
    if (senha.type === "password") {
        senha.type = "text";
    } else {
        senha.type = "password";
    }

}


const login = document.querySelector(".login-btn");

login.addEventListener("click", async (evento) => {
    evento.preventDefault();

    const emailInput = document.querySelector("#email");
    const senhaInput = document.querySelector("#password");

    const email = emailInput.value.trim();
    const senha = senhaInput.value.trim();

    if (email === "" || senha === "") {
        alert("Preencher todos os campos!");
        return;
    }

    const loginData = {
        email: email,
        senha: senha
    };

    try {
        const resposta = await fetch("http://localhost:8080/publico/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (!resposta.ok) {
            alert("Email ou senha inválidos!");
            return;
        }

        const token = await resposta.text();
        console.log("Login com sucesso:", token);
		
		localStorage.setItem("token", token);

        // Redireciona para a página de admin
        window.location.href = "/AdmHome.html";
    } catch (error) {
        alert("Erro ao fazer login: " + error);
    }
});



