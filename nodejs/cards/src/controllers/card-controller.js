const neDB = require('../configurations/database')
const api = {}

api.save = (request, response)=>{
    const my_canonical = request.body
    neDB.insert(my_canonical, (exception, card) =>{
        if(exception){
            const setence = "Houver um erro ao salvar o Card"
            console.error(setence, exception)
        }
        console.log('Card registrado com sucesso, ',card)
        response.status(201)
        response.json(card)
    })
}

api.findAll = (request, response)=>{
    neDB.find({}).sort({}).exec((exception, cards) =>{
        if(exception){
            const setence = "Erro ao listar os CArds"
            console.error(setence, exception)
            
            response.status(exception.status|400)
            response.json({'mensagem': setence})
        }
        //not err
        console.log('Lista de customers, ', cards)
        response.status(200)
        response.json(cards)
    })
}

api.update = (request, response)=>{
    const canonical = request.body
    const param = request.params.id //atualizando pelo id
    neDB.update({_id: param}, canonical, (exception)=>{
        if(exception){
            const setence = "Erro ao atualizar card"
            console.error(setence, exception)

            response.status(exception.status|400)
            response.json({'mensagem': setence})
        }
        console.log('Card Atualizado com sucesso')
        response.status(200)
        response.json('Card Atualizado com sucesso')
    })
}

api.remove = (request, response)=>{
    const param = request.params.id //removendo pelo id
    neDB.remove( {_id: param}, {}, (exception)=>{
        if(exception){
            const setence = "Erro ao remover o Card"
            console.error(setence, exception)

            response.status(exception.status|400)
            response.json({'mensagem': setence})
        }
        console.log('Card removido com sucesso, ')
        response.status(200)
        response.json('Card removido com sucesso')
    })
}

api.find = (request, response)=>{ //encontrar unico registro pelo id
    const param = request.params.id 
    neDB.find({_id: param}).exec((exception, card) =>{
        if(exception){
            const setence = "Erro ao carregar card"
            console.error(setence, exception)

            response.status(exception.status|400)
            response.json({'mensagem': setence})
        }
        console.log('Card carregado:, ', card)
        response.status(200)
        response.json(card)
    })
}
api.find = (request, response)=>{ //ordenar e paginar arquivos
    const param = request.body
    neDB.find({}).sort({customerName:1}).limit(2).exec(function (exception, cards) { //limit(2) indica que o limite de cards carregados por pagina é 2
        if(exception){
            const setence = "Erro ao carregar e organizar cards"
            console.error(setence, exception)

            response.status(exception.status|400)
            response.json({'mensagem': setence})
        }
        console.log('Cards carregados e organizados em paginas:, ', cards)
        response.status(200)
        response.json(cards)
    });
}

module.exports = api 