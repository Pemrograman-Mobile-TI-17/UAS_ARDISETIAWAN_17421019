const gitar = require('../model/Gitar.js')
const response = require('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId
exports.inputDatagitar = (data, gambar) =>
    new Promise(async (resolve, reject) =>{

        const gitarBaru = new gitar({
            kodeGitar : data.kodeGitar,
            tipeGitar : data.tipeGitar,
            jenisGitar: data.jenisGitar,
            merkGitar: data.merkGitar,
            hargaGitar: data.hargaGitar,
            gambar: gambar
        })

        await gitar.findOne({kodeGitar: data.kodeGitar})
            .then(gitar =>{
                if (gitar){
                    reject(response.commonErrorMsg('Kode Gitar Sudah Digunakan'))
                }else{
                    gitarBaru.save()
                        .then(r =>{
                            resolve(response.commonSuccessMsg('Berhasil Menginput Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf Input Gitar Gagal'))
                    })
                }
            }).catch(err =>{
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
        })
    })

exports.lihatDatagitar = () =>
    new Promise(async (resolve, reject) =>{
        await gitar.find({})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.lihatDetailDatagitar = (kodeGitar) =>
    new Promise(async (resolve, reject) =>{
        await gitar.findOne({kodeGitar: kodeGitar})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.updategitar = (id, data, gambar) =>
    new Promise(async (resolve, reject)=>{
        await gitar.updateOne(
            {_id : ObjectId(id)},
            {
                $set: {
                    kodeGitar : data.kodeGitar,
                    tipeGitar : data.tipeGitar,
                    jenisGitar: data.jenisGitar,
                    merkGitar: data.merkGitar,
                    hargaGitar: data.hargaGitar,
                    gambar: gambar
                }
            }
        ).then(b =>{
            resolve(response.commonSuccessMsg('Berhasil Mengubah Data'))
        }).catch(err =>{
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
        })
    })

exports.hapusgitar = (_id) =>
    new Promise(async (resolve, reject) =>{
        await gitar.remove({_id: ObjectId(_id)})
            .then(() =>{
                resolve(response.commonSuccessMsg('Berhasil Menghapus Data'))
            }).catch(() =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
            })
    })