import React, { Component } from 'react';
import $ from 'jquery';

class TabelaVeiculosDecada extends Component {

	render() {
		return(
                    <div>
                      <table className="pure-table">
                        <thead>
                          <tr>
                            <th>Decada</th>
                            <th>Quantidade</th>
                          </tr>
                        </thead>
                        <tbody>
                          {
                            this.props.lista.map(function(veiculos){
                              return (
                                <tr key={veiculos.decada}>
                                  <td>{veiculos.decada}</td>
                                  <td>{veiculos.quantidade}</td>
                                </tr>
                              );
                            })
                          }
                        </tbody>
                      </table>
                    </div>
		);
	}
}

export default class VeiculosDecadaBox extends Component {

  constructor() {
    super();
    this.state = {lista : []};
  }

  componentDidMount(){  
    $.ajax({
        url:"http://localhost:8080/veiculos/quantidade-por-decada",
        dataType: 'json',
        success:function(resposta){
          this.setState({lista:resposta});
        }.bind(this)
      } 
    );
  }
  render() {
    return(
        <div>
          <div className="header">
            <h1>Quantidade de carros por Decada</h1>
          </div>
          <div className="content" id="content">
            <TabelaVeiculosDecada lista={this.state.lista}/>
          </div>
        </div>
    );
  }
}