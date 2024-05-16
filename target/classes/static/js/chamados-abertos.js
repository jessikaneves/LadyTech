let selects = document.querySelectorAll('select.tecnico');

selects.forEach(
    document.addEventListener('change', el => function(){
        let valor = el.value
        let id = el.dataset.chamadoId
        // Parar o elemento
        el.disabled = true
        M.FormSelect.init(el)
        console.log(valor, id)
        // Fazer a chamada
            // alert de erro se for o caso
        // destravar elemento
    })
)
