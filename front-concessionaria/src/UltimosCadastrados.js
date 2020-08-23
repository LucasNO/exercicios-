import React, { Component } from 'react';
import $ from 'jquery';

class TabelaUltimosCadastrados extends Component {

    render() {
        return(
            <table className="pure-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Marca</th>
                    <th>Ano</th>
                    <th>Descrição</th>
                    <th>Vendido</th>
                    <th>Created</th>
                    <th>Updated</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.lista.map(function(veiculo){
                        return (
                            <tr key={veiculo.id}>
                                <td>{veiculo.id}</td>
                                <td>{veiculo.nome}</td>
                                <td>{veiculo.marca}</td>
                                <td>{veiculo.ano}</td>
                                <td>{veiculo.descricao}</td>
                                <td>{veiculo.vendido}</td>
                                <td>{veiculo.created}</td>
                                <td>{veiculo.updated}</td>
                            </tr>
                        );
                    })
                }
                </tbody>
            </table>
        );
    }
}

export default class UltimosCadastradosBox extends Component {


    constructor() {
        super();
        this.state = {lista : []};
    }

    componentDidMount(){
        $.ajax({
            url: "http://localhost:8080/veiculos/registrados-ultima-semana?pageNumber=0&pageSize=100",
            dataType: 'json',
            success: function(data) {
                console.log(data.content);
                this.setState({lista: data.content});
            }.bind(this)
        });
    }
    render() {
        return(
            <div>
                <div className="header">
                    <h1>Carros Cadastrados na Ultima Semana</h1>
                </div>
                <div className="content" id="content">
                    <TabelaUltimosCadastrados lista={this.state.lista}/>
                </div>
            </div>
        );
    }
}
