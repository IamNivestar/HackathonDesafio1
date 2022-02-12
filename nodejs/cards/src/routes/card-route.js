const api = require('../controllers/card-controller')

module.exports = (app) =>{
    app.route('/cards')
        .post(api.save)
        .get(api.findAll)
        
    app.route('/cards/:id')
        .get(api.find)
        .put(api.update)
        .delete(api.remove)
    app.route('/cards/paginationAndSorting')
        .get(api.find)
}