const mongoose = require('mongoose');

const userSchema = mongoose.Schema({

    kodeGitar: {
        type: String
    },
    tipeGitar: {
        type: String
    },
    jenisGitar: {
        type: String
    },
    merkGitar: {
        type: String
    },
    hargaGitar: {
        type: String
    },
    gambar: {
        type: String
    }
})
module.exports = mongoose.model('gitar', userSchema)