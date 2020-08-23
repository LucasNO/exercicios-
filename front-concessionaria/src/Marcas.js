import React, { Component } from 'react';
import $ from 'jquery';

class TabelaMarcas extends Component {

	render() {
		return(
                    <div>
                      <table className="pure-table">
                        <thead>
                          <tr>
                            <th>Marca</th>
                            <th>Quantidade</th>
                          </tr>
                        </thead>
                        <tbody>
                          {
                            this.props.lista.map(function(marca){
                              return (
                                <tr key={marca.marca}>
                                  <td>{marca.marca}</td>
                                  <td>{marca.quantidade}</td>
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

export default class MarcasBox extends Component {

  constructor() {
    super();
    this.state = {lista : []};
  }

  componentDidMount(){  
    $.ajax({
        url:"http://localhost:8080/marcas/veiculos-por-fabricante",
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
            <h1>Quantidade de carros por Marca</h1>
          </div>
          <div className="content" id="content">
            <TabelaMarcas lista={this.state.lista}/>
          </div>
        </div>
    );
  }
}