let language = "python"
let code = "Write your code here..."

function languageSelector(newValue) {
    language = newValue
}

function executeCode() {
    code = document.getElementById("code").value
    let data = {
        code: code,
        language: language,
    }

    fetch("http://0.0.0.0:8080/run", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(data)
        }
    )
        .then(response => response.json())
        .then(data => {
            document.getElementById("output").value = data['output'];
        })
        .catch((error) => {
            console.error(error);
        });
}